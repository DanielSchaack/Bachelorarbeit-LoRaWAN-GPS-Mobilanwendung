package com.project.danielbachelor.routenergebnis;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.danielbachelor.R;
import com.project.danielbachelor.funktionen.Generell;

public class routenergebnisActivity extends AppCompatActivity {
    private routenergebnisPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

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
}
