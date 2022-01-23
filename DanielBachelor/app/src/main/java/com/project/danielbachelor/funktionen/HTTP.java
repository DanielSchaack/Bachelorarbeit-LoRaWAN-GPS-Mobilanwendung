package com.project.danielbachelor.funktionen;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.project.danielbachelor.R;
import com.project.danielbachelor.mSingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HTTP {
    public static void JSONRequest(Context AppKontext, String API, int HTTPMethode, JSONObject mJSONObject, Response.Listener<JSONObject> onResponse, Response.ErrorListener onErrorResponse){
        String URLStart = AppKontext.getString(R.string.API_Adresse_Basis);
        String URL = URLStart + API;

        JsonObjectRequest mJSONObjectRequest = new JsonObjectRequest(HTTPMethode, URL, mJSONObject, onResponse, onErrorResponse){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        mSingleton.getInstance(AppKontext).addToRequestQueue(mJSONObjectRequest);
    }
}
