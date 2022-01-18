package com.project.danielbachelor.hauptmenu;

import com.project.danielbachelor.anmeldung.anmeldungView;

public class hauptmenuPresenter implements hauptmenuKontrakt.Presenter{
    private final hauptmenuView mView;
    public hauptmenuPresenter(hauptmenuView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }
    @Override
    public void start() {

    }
}
