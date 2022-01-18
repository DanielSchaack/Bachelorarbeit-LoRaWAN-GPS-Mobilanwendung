package com.project.danielbachelor.profil;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.project.danielbachelor.R;
import com.project.danielbachelor.funktionen.Generell;
import com.project.danielbachelor.hwsuebersicht.hwsuebersichtPresenter;
import com.project.danielbachelor.hwsuebersicht.hwsuebersichtView;

public class profilActivity extends AppCompatActivity {
    private profilPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

        //Toolbar-Setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Profil");                       //to-do String Value
        ab.setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        ab.setDisplayHomeAsUpEnabled(true);

        //Fragment-Setup
        profilView AV = (profilView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        //Falls AV nicht vorhanden ist, dann erstelle das View-Fragment und füge dies der Aktivität hinzu
        if (AV == null) {
            AV = profilView.newInstance();
            Generell.addFragmentToActivity(getSupportFragmentManager(), AV, R.id.contentFrame);
        }

        mPresenter = new profilPresenter(AV);
    }
}
