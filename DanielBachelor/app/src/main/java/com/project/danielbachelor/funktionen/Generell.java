package com.project.danielbachelor.funktionen;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.project.danielbachelor.HWSRecAdapter;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.Comparator;
import java.util.List;

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

    public static void fuegeSortierFunktionZuButtonVonRecView(List<standort> StandortListe, Button BGButon, Button LGButton, Button ZeitButton, RecyclerView HWSUebersichtRecView){
        BGButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandortListe.sort(Comparator.comparing(standort::getSBreitengrad));
                HWSUebersichtRecView.setAdapter(new HWSRecAdapter(StandortListe));
                HWSUebersichtRecView.getAdapter().notifyDataSetChanged();
            }
        });

        LGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandortListe.sort(Comparator.comparing(standort::getSLaengengrad));
                HWSUebersichtRecView.setAdapter(new HWSRecAdapter(StandortListe));
                HWSUebersichtRecView.getAdapter().notifyDataSetChanged();
            }
        });

        ZeitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StandortListe.sort(Comparator.comparing(standort::getSErfassungszeit));
                HWSUebersichtRecView.setAdapter(new HWSRecAdapter(StandortListe));
                HWSUebersichtRecView.getAdapter().notifyDataSetChanged();
            }
        });

        HWSUebersichtRecView.setAdapter(new HWSRecAdapter(StandortListe));
        HWSUebersichtRecView.getAdapter().notifyDataSetChanged();
    }
}
