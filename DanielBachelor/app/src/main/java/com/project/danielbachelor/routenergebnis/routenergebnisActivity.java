package com.project.danielbachelor.routenergebnis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;

import java.io.Serializable;
import java.util.List;

public class routenergebnisActivity extends AppCompatActivity {
    private routenergebnisPresenter mPresenter;
    public static String StandortListeTag = "StandortListeTag";
    private List<standort> mStandortListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

        if(savedInstanceState != null){
            mStandortListe = (List<standort>) savedInstanceState.getSerializable(StandortListeTag);
        }else{
            mStandortListe = (List<standort>) getIntent().getSerializableExtra(StandortListeTag);
        }

        //Toolbar-Setup
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Registrierung");                       //to-do String Value

        //Fragment-Setup
        routenergebnisView AV = (routenergebnisView) getSupportFragmentManager().findFragmentById(R.id.contentFrameKarte);
        //Falls AV nicht vorhanden ist, dann erstelle das View-Fragment und füge dies der Aktivität hinzu
        if (AV == null) {
            AV = routenergebnisView.newInstance();
            Generell.addFragmentToActivity(getSupportFragmentManager(), AV, R.id.contentFrameKarte);
        }

        mPresenter = new routenergebnisPresenter(AV);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(StandortListeTag, (Serializable) mStandortListe);
    }
}
