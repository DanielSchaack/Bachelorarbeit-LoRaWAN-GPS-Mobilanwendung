package com.project.danielbachelor.registrierung;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.project.danielbachelor.R;
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.funktionen.HTTP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class registrierungPresenter implements registrierungKontrakt.Presenter{
    private final registrierungView mView;
    public registrierungPresenter(registrierungView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void sendeRegistrierung(Context Kontext, String Benutzername, String Passwort) {
        int HTTPMethode = Request.Method.POST;
        JSONObject mJSONObject= new JSONObject();

        Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String ErfolgString = "Erfolgreich registriert!";
                Generell.posteToast(Kontext, ErfolgString);
            }
        };

        Response.ErrorListener mError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String Grund= "";
                String ErrorDataString = new String(error.networkResponse.data);
                int errorStatusCode = error.networkResponse.statusCode;
                try {
                    JSONObject mJSONObject = new JSONObject(ErrorDataString);
                    Grund = mJSONObject.getString(Kontext.getString(R.string.API_message));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String ErrorString = "Fehler bei der Registrierung:\n"+ Grund;
                Generell.posteToast(Kontext, ErrorString);
            }
        };

        String registrierungAPIString = Kontext.getString(R.string.API_Adresse_Benutzer_Registrierung);

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
                HTTP.JSONRequest(Kontext.getApplicationContext(), registrierungAPIString, HTTPMethode, mJSONObject, mListener, mError);
            }
        });
    }
}
