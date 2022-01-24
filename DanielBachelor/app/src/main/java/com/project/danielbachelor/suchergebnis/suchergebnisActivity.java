package com.project.danielbachelor.suchergebnis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;

import java.io.Serializable;
import java.util.List;

public class suchergebnisActivity extends AppCompatActivity {
    public static String StandortListeTag = "StandortListeTag";
    private List<standort> mStandortListe;

    private suchergebnisPresenter mPresenter;

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
        ab.setTitle("Suchergebnis");                       //to-do String Value
        ab.setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        ab.setDisplayHomeAsUpEnabled(true);

        //Fragment-Setup
        suchergebnisView AV = (suchergebnisView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        //Falls AV nicht vorhanden ist, dann erstelle das View-Fragment und füge dies der Aktivität hinzu
        if (AV == null) {
            AV = suchergebnisView.newInstance();
            Generell.addFragmentToActivity(getSupportFragmentManager(), AV, R.id.contentFrame);
        }

        mPresenter = new suchergebnisPresenter(AV);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(StandortListeTag, (Serializable) mStandortListe);
    }
}
