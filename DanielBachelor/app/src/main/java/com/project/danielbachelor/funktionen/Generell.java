package com.project.danielbachelor.funktionen;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Generell {

    //Füge ein Fragment an eine Activity
    public static void addFragmentToActivity(FragmentManager FM, Fragment F, int FrameID){
        if(FM!=null && F !=null){
            FragmentTransaction T = FM.beginTransaction();
            T.add(FrameID, F);
            T.commit();
        }
    }

    public static void posteToast(Context Kontext, String Nachricht){
        Toast.makeText(Kontext, Nachricht, Toast.LENGTH_SHORT).show();
    }
}
