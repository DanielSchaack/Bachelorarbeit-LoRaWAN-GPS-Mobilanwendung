package com.project.danielbachelor.anmeldung;

public class anmeldungPresenter implements anmeldungKontrakt.Presenter{
    private final anmeldungView mView;
    public anmeldungPresenter(anmeldungView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
