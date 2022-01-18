package com.project.danielbachelor.suchergebnis;

public class suchergebnisPresenter implements suchergebnisKontrakt.Presenter {
    private final suchergebnisView mView;
    public suchergebnisPresenter(suchergebnisView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
