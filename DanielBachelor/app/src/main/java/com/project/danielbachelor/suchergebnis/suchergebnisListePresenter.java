package com.project.danielbachelor.suchergebnis;

import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.List;

public class suchergebnisListePresenter implements suchergebnisKontrakt.ListePresenter {
    private final suchergebnisListeView mView;
    private final List<standort> mStandortListe;
    public suchergebnisListePresenter(suchergebnisListeView AV, List<standort> newStandortListe) {
        this.mView = AV;
        this.mStandortListe = newStandortListe;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setzeRecViewUndSortierButtons(mStandortListe);
    }
}
