package com.project.danielbachelor.profil;

import android.content.Context;
import android.content.Intent;

import com.project.danielbachelor.anmeldung.anmeldungActivity;

public class profilPresenter implements profilKontrakt.Presenter{
    private final profilView mView;
    private final String mBenutzername;
    public profilPresenter(profilView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.setzeBenutzername(mBenutzername);
    }

    @Override
    public void meldeAb(Context Kontext) {
        Intent mIntent = new Intent(Kontext, anmeldungActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Kontext.startActivity(mIntent);
    }
}
