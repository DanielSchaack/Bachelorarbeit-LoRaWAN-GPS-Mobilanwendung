package com.project.danielbachelor.anmeldung;

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
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.funktionen.HTTP;
import com.project.danielbachelor.hauptmenu.hauptmenuActivity;
import com.project.danielbachelor.registrierung.registrierungActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class anmeldungPresenter implements anmeldungKontrakt.Presenter{
    private final anmeldungView mView;
    public anmeldungPresenter(anmeldungView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        //Mache was, was bei jedem Aufruf des Bildschirms ausgeführt werden muss
    }

    @Override
    public void aktiviereRegistrierungSicht(Context Kontext) {
        Intent mIntent = new Intent(Kontext, registrierungActivity.class);
        Kontext.startActivity(mIntent);
    }

    @Override
    public void aktiviereHauptmenuSicht(Context Kontext, String Benutzername, String Passwort) {
        int HTTPMethode = Request.Method.POST;
        JSONObject mJSONObject= new JSONObject();

        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String ErfolgString = Kontext.getString(R.string.Toast_Erfolg_Anmeldung);
                Generell.posteToast(Kontext, ErfolgString);

                Intent mIntent = new Intent(Kontext, hauptmenuActivity.class);
                mIntent.putExtra(hauptmenuActivity.Benutzername_Tag, Benutzername);
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
                String ErrorString = Kontext.getString(R.string.Toast_Fehler_Anmeldung)+ Grund;
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        String APIString = Kontext.getString(R.string.API_Adresse_Benutzer_Anmeldung);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                try {
                    mJSONObject.put(Kontext.getString(R.string.API_name), Benutzername);
                    mJSONObject.put(Kontext.getString(R.string.API_passwort), Passwort);
                    mJSONObject.put(Kontext.getString(R.string.API_token), task.getResult());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HTTP.JSONRequest(Kontext.getApplicationContext(), APIString, HTTPMethode, mJSONObject, mListener, mError);
            }
        });
    }
}
