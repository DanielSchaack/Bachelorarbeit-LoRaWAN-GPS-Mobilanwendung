package com.project.danielbachelor.suche;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.funktionen.HTTP;
import com.project.danielbachelor.hwsuebersicht.hwsuebersichtActivity;
import com.project.danielbachelor.hwsuebersicht.hwsuebersichtKontrakt;
import com.project.danielbachelor.suchergebnis.suchergebnisActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class suchePresenter implements sucheKontrakt.Presenter{
    private final sucheView mView;
    private final String mBenutzername;
    public suchePresenter(sucheView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setzeStandardWerte();
        getVerknuepfungen(mView.getContext());
    }

    @Override
    public void fuehreSucheDurch(Context Kontext,String HName, String DatumEins, String DatumZwei, String BGEins, String BGZwei, String LGEins, String LGZwei) {
        boolean BGVorhanden = false, LGVorhanden = false;

        if(Double.parseDouble(BGEins)!=Double.parseDouble(BGEins)){
            BGVorhanden = true;
        }

        if(Double.parseDouble(LGEins)!=Double.parseDouble(LGZwei)){
            LGVorhanden = true;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String zunten = dtf2.format(dtf.parse(DatumEins));
        String zoben = dtf2.format(dtf.parse(DatumZwei));

        int HTTPMethode = Request.Method.POST;
        JSONObject mJSONObject= new JSONObject();

        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<standort> StandortListe = new ArrayList<standort>();

                try {
                    JSONArray mJSONOArray = response.getJSONArray(Kontext.getString(R.string.API_data));

                    for(int i=0; i<mJSONOArray.length();i++){
                        JSONObject currentJSON = mJSONOArray.getJSONObject(i);
                        String HName = currentJSON.getString("HName");
                        double BGrad = currentJSON.getDouble("SBreitengrad");
                        double LGrad = currentJSON.getDouble("SLaengengrad");

                        String SErfassungszeit = currentJSON.getString("SErfassungszeit");
                        String SErfassungszeitOhneTundOhneZ = SErfassungszeit.replace('T', ' ').replace('Z', ' ');
                        Timestamp DateTime = Timestamp.valueOf(SErfassungszeitOhneTundOhneZ);
                        StandortListe.add(new standort(0, DateTime, BGrad, LGrad, HName));//max(Standort.SErfassungszeit) -> 2022-01-16T19:28:23.000Z
                    }
                    Intent mIntent = new Intent(Kontext, suchergebnisActivity.class);
                    mIntent.putExtra(suchergebnisActivity.StandortListeTag, (Serializable) StandortListe);
                    Kontext.startActivity(mIntent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener mError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String Grund= "";
                String ErrorDataString = new String(error.networkResponse.data);
                try {
                    JSONObject mJSONObject = new JSONObject(ErrorDataString);
                    Grund = mJSONObject.getString(Kontext.getString(R.string.API_message));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String ErrorString = Kontext.getString(R.string.Toast_Fehler_Suche)+ Grund;
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        try {
            mJSONObject.put(Kontext.getString(R.string.API_hname), HName);
            mJSONObject.put(Kontext.getString(R.string.API_zunten), zunten);
            mJSONObject.put(Kontext.getString(R.string.API_zoben), zoben);

            mJSONObject.put(Kontext.getString(R.string.API_bgvorhanden), BGVorhanden);
            mJSONObject.put(Kontext.getString(R.string.API_bgunten), Double.parseDouble(BGZwei));
            mJSONObject.put(Kontext.getString(R.string.API_bgoben), Double.parseDouble(BGEins));

            mJSONObject.put(Kontext.getString(R.string.API_lgvorhanden), LGVorhanden);
            mJSONObject.put(Kontext.getString(R.string.API_lgunten), Double.parseDouble(LGZwei));
            mJSONObject.put(Kontext.getString(R.string.API_lgoben), Double.parseDouble(LGEins));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String APIString = Kontext.getString(R.string.API_Adresse_Standort_Suche);
        HTTP.JSONRequest(Kontext.getApplicationContext(), APIString, HTTPMethode, mJSONObject, mListener, mError);
    }

    @Override
    public void fuehreDateTimePickerDurch(View view) {
        LocalDateTime dateTime = LocalDateTime.now();
        new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker DPview, int year, int monthOfYear, int dayOfMonth) {
                LocalDateTime temp = dateTime.withYear(year).withMonth(monthOfYear+1).withDayOfMonth(dayOfMonth);
                new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker TPview, int hourOfDay, int minute) {
                        dateTime.withHour(hourOfDay).withMinute(minute);
                        mView.setzeEditText(view, temp.withHour(hourOfDay).withMinute(minute).truncatedTo(ChronoUnit.MINUTES));
                    }
                }, dateTime.getHour(), dateTime.getMinute(), true).show();
            }
        }, dateTime.getYear(), dateTime.getMonthValue()-1, dateTime.getDayOfMonth()).show();

    }

    public void getVerknuepfungen(Context Kontext){
        int HTTPMethode = Request.Method.POST;
        JSONObject mJSONObject= new JSONObject();

        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<standort> StandortListe = new ArrayList<standort>();

                try {
                    JSONArray mJSONOArray = response.getJSONArray(Kontext.getString(R.string.API_data));

                    for(int i=0; i<mJSONOArray.length();i++){
                        JSONObject currentJSON = mJSONOArray.getJSONObject(i);
                        String HName = currentJSON.getString("HName");
                        double BGrad = currentJSON.getDouble("SBreitengrad");
                        double LGrad = currentJSON.getDouble("SLaengengrad");

                        String SErfassungszeit = currentJSON.getString("max(Standort.SErfassungszeit)");
                        String SErfassungszeitOhneTundOhneZ = SErfassungszeit.replace('T', ' ').replace('Z', ' ');
                        Timestamp DateTime = Timestamp.valueOf(SErfassungszeitOhneTundOhneZ);
                        StandortListe.add(new standort(0, DateTime, BGrad, LGrad, HName));//max(Standort.SErfassungszeit) -> 2022-01-16T19:28:23.000Z
                    }

                    mView.setzeSpinner(StandortListe);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener mError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String Grund= "";
                String ErrorDataString = new String(error.networkResponse.data);
                try {
                    JSONObject mJSONObject = new JSONObject(ErrorDataString);
                    Grund = mJSONObject.getString(Kontext.getString(R.string.API_message));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String ErrorString = Kontext.getString(R.string.Toast_Fehler_HWSUebersicht)+ Grund;
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        String APIString = Kontext.getString(R.string.API_Adresse_Hardwaresystem_Verknuepfunguebersicht);

        try {
            mJSONObject.put(Kontext.getString(R.string.API_bname), mBenutzername);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HTTP.JSONRequest(Kontext.getApplicationContext(), APIString, HTTPMethode, mJSONObject, mListener, mError);
    }
}
