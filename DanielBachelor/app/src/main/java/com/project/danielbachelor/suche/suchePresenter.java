package com.project.danielbachelor.suche;

import com.project.danielbachelor.routenergebnis.routenergebnisView;

public class suchePresenter implements sucheKontrakt.Presenter{
    private final sucheView mView;
    public suchePresenter(sucheView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
