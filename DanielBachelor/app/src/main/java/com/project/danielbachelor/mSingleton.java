package com.project.danielbachelor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class mSingleton {
    private static mSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private mSingleton(Context Kontext){
        ctx = Kontext;
        requestQueue = getRequestQueue();
    }

    public static synchronized mSingleton getInstance(Context Kontext){
        if(instance == null){
            instance = new mSingleton(Kontext);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
