package com.project.danielbachelor.suchergebnis;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.funktionen.HTTP;
import com.project.danielbachelor.routenergebnis.routenergebnisActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class suchergebnisListePresenter implements suchergebnisKontrakt.ListePresenter {
    private final suchergebnisListeView mView;
    private final List<standort> mStandortListe;
    public suchergebnisListePresenter(suchergebnisListeView AV, List<standort> newStandortListe) {
        this.mView = AV;
        this.mStandortListe = newStandortListe;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setzeRecViewUndSortierButtons(mStandortListe);
    }

    @Override
    public void fuehreRoutensucheDurch(Context Kontext) {
        int HTTPMethode = Request.Method.GET;
        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<standort> StandortListe = new ArrayList<standort>();

                try {
                    JSONArray mJSONOArray = response.getJSONArray(Kontext.getString(R.string.API_data));
                    /*
                    for(int i=0; i<mJSONOArray.length();i++){
                        JSONObject currentJSON = mJSONOArray.getJSONObject(i);
                        String HName = currentJSON.getString("HName");
                        double BGrad = currentJSON.getDouble("SBreitengrad");
                        double LGrad = currentJSON.getDouble("SLaengengrad");

                        String SErfassungszeit = currentJSON.getString("max(Standort.SErfassungszeit)");
                        String SErfassungszeitOhneTundOhneZ = SErfassungszeit.replace('T', ' ').replace('Z', ' ');
                        Timestamp DateTime = Timestamp.valueOf(SErfassungszeitOhneTundOhneZ);
                        StandortListe.add(new standort(0, DateTime, BGrad, LGrad, HName));//max(Standort.SErfassungszeit) -> 2022-01-16T19:28:23.000Z

                    }*/

                    Intent mIntent = new Intent(Kontext, routenergebnisActivity.class);
                    //mIntent.putExtra(routenergebnisActivity.StandortListeTag, (Serializable) StandortListe);
                    Kontext.startActivity(mIntent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener mError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String ErrorString = Kontext.getString(R.string.Toast_Fehler_Routen);
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        String KoordinatenAlsString = "";
        for (int i = 0; i < mStandortListe.size(); i++) {
            KoordinatenAlsString += String.valueOf(mStandortListe.get(i).getSBreitengrad())+",";
            KoordinatenAlsString += String.valueOf(mStandortListe.get(i).getSLaengengrad())+";";
        }
        HTTP.JSONRequest(Kontext.getApplicationContext(), KoordinatenAlsString, HTTPMethode, null, mListener, mError);
    }
}
