package com.project.danielbachelor.profil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.project.danielbachelor.R;
import com.project.danielbachelor.funktionen.Generell;

public class profilActivity extends AppCompatActivity {
    public static String Benutzername_Tag = "Benutzername_Tags";
    private String mBenutzername;

    private profilPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

        if(savedInstanceState != null){
            mBenutzername = savedInstanceState.getString(Benutzername_Tag);
        }else{
            mBenutzername = getIntent().getStringExtra(Benutzername_Tag);
        }

        //Toolbar-Setup
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Profil");                       //to-do String Value

        //Fragment-Setup
        profilView AV = (profilView) getSupportFragmentManager().findFragmentById(R.id.contentFrameKarte);
        //Falls AV nicht vorhanden ist, dann erstelle das View-Fragment und füge dies der Aktivität hinzu
        if (AV == null) {
            AV = profilView.newInstance();
            Generell.addFragmentToActivity(getSupportFragmentManager(), AV, R.id.contentFrameKarte);
        }

        mPresenter = new profilPresenter(AV, mBenutzername);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Benutzername_Tag, mBenutzername);
    }
}
