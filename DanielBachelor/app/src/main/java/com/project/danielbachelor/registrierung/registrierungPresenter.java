package com.project.danielbachelor.registrierung;

import com.project.danielbachelor.profil.profilView;

public class registrierungPresenter implements registrierungKontrakt.Presenter{
    private final registrierungView mView;
    public registrierungPresenter(registrierungView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
