package com.project.danielbachelor.anmeldung;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.danielbachelor.R;
import com.project.danielbachelor.funktionen.Generell;

public class anmeldungActivity extends AppCompatActivity {
    private anmeldungPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

        //Toolbar-Setup
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Anmeldung");                       //to-do String Value

        //Fragment-Setup
        anmeldungView AV = (anmeldungView) getSupportFragmentManager().findFragmentById(R.id.contentFrameKarte);
        //Falls AV nicht vorhanden ist, dann erstelle das View-Fragment und füge dies der Aktivität hinzu
        if(AV == null){
            AV = anmeldungView.newInstance();
            Generell.addFragmentToActivity(getSupportFragmentManager(), AV, R.id.contentFrameKarte);
        }

        mPresenter = new anmeldungPresenter(AV);

    }
}
