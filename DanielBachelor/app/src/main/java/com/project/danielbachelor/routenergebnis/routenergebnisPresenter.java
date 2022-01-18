package com.project.danielbachelor.routenergebnis;

import com.project.danielbachelor.registrierung.registrierungView;

public class routenergebnisPresenter implements routenergebnisKontrakt.Presenter{
    private final routenergebnisView mView;
    public routenergebnisPresenter(routenergebnisView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
