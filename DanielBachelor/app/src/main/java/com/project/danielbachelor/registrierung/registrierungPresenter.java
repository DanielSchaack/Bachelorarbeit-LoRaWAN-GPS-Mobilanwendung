package com.project.danielbachelor.registrierung;

public class registrierungPresenter implements registrierungKontrakt.Presenter{
    private final registrierungView mView;
    public registrierungPresenter(registrierungView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void sendeRegistrierung(String Benutzername, String Passwort) {

    }
}
