package com.project.danielbachelor.suchergebnis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;

import java.io.Serializable;
import java.util.List;

public class suchergebnisActivity extends AppCompatActivity {
    public static String StandortListeTag = "StandortListeTag";
    private List<standort> mStandortListe;

    private suchergebnisListePresenter mPresenterListe;
    private suchergebnisKartePresenter mPresenterKarte;

    private TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suchergebnis);

        if(savedInstanceState != null){
            mStandortListe = (List<standort>) savedInstanceState.getSerializable(StandortListeTag);
        }else{
            mStandortListe = (List<standort>) getIntent().getSerializableExtra(StandortListeTag);
        }

        //Toolbar-Setup
        ActionBar ab = getSupportActionBar();
        ab.setTitle("Suchergebnis");                       //to-do String Value

        mTabLayout = findViewById(R.id.TabLayout);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()){
                    case 0: {
                        suchergebnisListeView ALV = (suchergebnisListeView) getSupportFragmentManager().findFragmentById(R.id.contentFrameListe);
                        if (ALV == null) {
                            ALV = suchergebnisListeView.newInstance();
                            Generell.addFragmentToActivity(getSupportFragmentManager(), ALV, R.id.contentFrameListe);
                        }else{
                            Generell.showFragment(getSupportFragmentManager(), ALV);
                        }

                        suchergebnisKarteView AKV = (suchergebnisKarteView) getSupportFragmentManager().findFragmentById(R.id.contentFrameKarte);
                        if (AKV != null){
                            Generell.hideFragment(getSupportFragmentManager(), AKV);
                        }
                            mPresenterListe = new suchergebnisListePresenter(ALV, mStandortListe);
                        break;
                    }
                    case 1: {
                        suchergebnisKarteView AKV = (suchergebnisKarteView) getSupportFragmentManager().findFragmentById(R.id.contentFrameKarte);
                        if (AKV == null) {
                            AKV = suchergebnisKarteView.newInstance();
                            Generell.addFragmentToActivity(getSupportFragmentManager(), AKV, R.id.contentFrameKarte);
                        }else{
                            Generell.showFragment(getSupportFragmentManager(), AKV);
                        }

                        suchergebnisListeView ALV = (suchergebnisListeView) getSupportFragmentManager().findFragmentById(R.id.contentFrameListe);
                        if (ALV != null){
                            Generell.hideFragment(getSupportFragmentManager(), ALV);
                        }
                        mPresenterKarte = new suchergebnisKartePresenter(AKV, mStandortListe);
                        break;
                    }
                    default: break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });

        mTabLayout.selectTab(mTabLayout.getTabAt(0));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(StandortListeTag, (Serializable) mStandortListe);
    }
}
