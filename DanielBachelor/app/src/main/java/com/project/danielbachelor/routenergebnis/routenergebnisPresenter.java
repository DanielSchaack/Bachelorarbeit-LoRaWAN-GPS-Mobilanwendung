package com.project.danielbachelor.routenergebnis;

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
