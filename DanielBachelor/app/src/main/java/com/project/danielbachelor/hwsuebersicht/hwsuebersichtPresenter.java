package com.project.danielbachelor.hwsuebersicht;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.funktionen.HTTP;
import com.project.danielbachelor.hwshinzufuegen.hwshinzufuegenActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class hwsuebersichtPresenter implements hwsuebersichtKontrakt.Presenter{
    private final hwsuebersichtView mView;
    private final String mBenutzername;
    public hwsuebersichtPresenter(hwsuebersichtView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        getVerknuepfungen(mView.getContext());
    }

    @Override
    public void aktiviereHWSHinzufuegen(Context Kontext) {
        Intent mIntent = new Intent(Kontext, hwshinzufuegenActivity.class);
        mIntent.putExtra(hwshinzufuegenActivity.Benutzername_Tag, mBenutzername);
        Kontext.startActivity(mIntent);
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

                    mView.setzeRecViewUndSortierFunktion(StandortListe);
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
