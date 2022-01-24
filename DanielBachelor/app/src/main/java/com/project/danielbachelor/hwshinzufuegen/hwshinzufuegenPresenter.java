package com.project.danielbachelor.hwshinzufuegen;

import android.content.Context;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.funktionen.HTTP;
import com.project.danielbachelor.hauptmenu.hauptmenuActivity;
import com.project.danielbachelor.hwsuebersicht.hwsuebersichtActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class hwshinzufuegenPresenter implements hwshinzufuegenKontrakt.Presenter{
    private final hwshinzufuegenView mView;
    private final String mBenutzername;
    public hwshinzufuegenPresenter(hwshinzufuegenView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        getAlleHWS(mView.getContext());
    }

    public void getAlleHWS(Context Kontext){
        int HTTPMethode = Request.Method.GET;
        JSONObject mJSONObject= new JSONObject();

        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<String> HNameListe = new ArrayList<String>();

                try {
                    JSONArray mJSONOArray = response.getJSONArray(Kontext.getString(R.string.API_data));

                    for(int i=0; i<mJSONOArray.length();i++){
                        JSONObject currentJSON = mJSONOArray.getJSONObject(i);
                        String HName = currentJSON.getString("HName");
                        HNameListe.add(HName);
                    }
                    mView.setzeRecView(HNameListe);
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
                String ErrorString = Kontext.getString(R.string.Toast_Fehler_AlleUebersicht)+ Grund;
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        String APIString = Kontext.getString(R.string.API_Adresse_Hardwaresystem_Ubersicht);
        HTTP.JSONRequest(Kontext.getApplicationContext(), APIString, HTTPMethode, null, mListener, mError);
    }

    @Override
    public void oeffneDialogZumVerknuepfen(String HName) {
        mView.zeigeVerknuepfungDialog(HName);
    }

    @Override
    public void verknuepfeHardwaresystem(Context Kontext, String HName, String Passwort) {
        int HTTPMethode = Request.Method.POST;
        JSONObject mJSONObject= new JSONObject();

        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String ErfolgString = Kontext.getString(R.string.Toast_Erfolg_Verknuepfung);
                Generell.posteToast(Kontext, ErfolgString);

                Intent mIntent = new Intent(Kontext, hwsuebersichtActivity.class);
                mIntent.putExtra(hwsuebersichtActivity.Benutzername_Tag, mBenutzername);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Kontext.startActivity(mIntent);
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
                String ErrorString = Kontext.getString(R.string.Toast_Fehler_Verknuepfung)+ Grund;
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        try {
            mJSONObject.put(Kontext.getString(R.string.API_bname), mBenutzername);
            mJSONObject.put(Kontext.getString(R.string.API_hname), HName);
            mJSONObject.put(Kontext.getString(R.string.API_hpasswort), Passwort);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String APIString = Kontext.getString(R.string.API_Adresse_Hardwaresystem_Verknuepfung);
        HTTP.JSONRequest(Kontext.getApplicationContext(), APIString, HTTPMethode, mJSONObject, mListener, mError);
    }
}
