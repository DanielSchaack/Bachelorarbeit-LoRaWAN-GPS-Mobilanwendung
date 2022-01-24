package com.project.danielbachelor.suchergebnis;

import android.content.Context;

import com.project.danielbachelor.datenbank.entitaet.standort;

import org.osmdroid.views.MapView;

import java.util.List;

public class suchergebnisKartePresenter implements suchergebnisKontrakt.KartePresenter{
    private final suchergebnisKarteView mView;
    private final List<standort> mStandortListe;
    public suchergebnisKartePresenter(suchergebnisKarteView AV, List<standort> newStandortListe) {
        this.mView = AV;
        this.mStandortListe = newStandortListe;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

        mView.setzeGeoPoints(mStandortListe);
    }


    @Override
    public void fuehreRoutensucheDurch(Context Kontext) {

    }
}
