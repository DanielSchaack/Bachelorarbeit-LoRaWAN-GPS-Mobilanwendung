package com.project.danielbachelor.hauptmenu;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.danielbachelor.R;
import com.project.danielbachelor.anmeldung.anmeldungPresenter;
import com.project.danielbachelor.anmeldung.anmeldungView;
import com.project.danielbachelor.funktionen.Generell;

public class hauptmenuActivity extends AppCompatActivity {
    private hauptmenuPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

        //Toolbar-Setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Hauptmenü");                       //to-do String Value
        ab.setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        ab.setDisplayHomeAsUpEnabled(true);

        //Fragment-Setup
        hauptmenuView AV = (hauptmenuView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        //Falls AV nicht vorhanden ist, dann erstelle das View-Fragment und füge dies der Aktivität hinzu
        if(AV == null){
            AV = hauptmenuView.newInstance();
            Generell.addFragmentToActivity(getSupportFragmentManager(), AV, R.id.contentFrame);
        }

        mPresenter = new hauptmenuPresenter(AV);
    }
}
