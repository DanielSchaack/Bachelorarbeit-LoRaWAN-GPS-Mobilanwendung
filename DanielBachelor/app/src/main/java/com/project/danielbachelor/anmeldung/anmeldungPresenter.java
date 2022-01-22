package com.project.danielbachelor.anmeldung;

import android.content.Context;
import android.content.Intent;

import com.project.danielbachelor.hauptmenu.hauptmenuActivity;
import com.project.danielbachelor.registrierung.registrierungActivity;

public class anmeldungPresenter implements anmeldungKontrakt.Presenter{
    private final anmeldungView mView;
    public anmeldungPresenter(anmeldungView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        //Mache was, was bei jedem Aufruf des Bildschirms ausgeführt werden muss
    }

    @Override
    public void aktiviereRegistrierungSicht(Context Kontext) {
        Intent mIntent = new Intent(Kontext, registrierungActivity.class);
        Kontext.startActivity(mIntent);
    }

    @Override
    public void aktiviereHauptmenuSicht(Context Kontext, String Benutzername, String Passwort) {
        Intent mIntent = new Intent(Kontext, hauptmenuActivity.class);
        mIntent.putExtra(hauptmenuActivity.Benutzername_Tag, Benutzername);
        Kontext.startActivity(mIntent);
    }
}
