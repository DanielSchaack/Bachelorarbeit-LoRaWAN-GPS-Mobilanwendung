package com.project.danielbachelor.profil;

public class profilPresenter implements profilKontrakt.Presenter{
    private final profilView mView;
    public profilPresenter(profilView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
