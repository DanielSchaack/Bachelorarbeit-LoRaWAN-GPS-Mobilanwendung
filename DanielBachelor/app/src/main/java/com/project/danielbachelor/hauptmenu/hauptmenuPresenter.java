package com.project.danielbachelor.hauptmenu;

import android.content.Context;
import android.content.Intent;

import com.project.danielbachelor.hwsuebersicht.hwsuebersichtActivity;
import com.project.danielbachelor.profil.profilActivity;
import com.project.danielbachelor.suche.sucheActivity;

public class hauptmenuPresenter implements hauptmenuKontrakt.Presenter{
    private final hauptmenuView mView;
    private final String mBenutzername;
    public hauptmenuPresenter(hauptmenuView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }
    @Override
    public void start() {

    }

    @Override
    public void aktiviereSucheSicht(Context Kontext) {
        Intent mIntent = new Intent(Kontext, sucheActivity.class);
        mIntent.putExtra(sucheActivity.Benutzername_Tag, mBenutzername);
        Kontext.startActivity(mIntent);
    }

    @Override
    public void aktiviereProfilSicht(Context Kontext) {
        Intent mIntent = new Intent(Kontext, profilActivity.class);
        mIntent.putExtra(profilActivity.Benutzername_Tag, mBenutzername);
        Kontext.startActivity(mIntent);
    }

    @Override
    public void aktiviereHWSUebersichtSicht(Context Kontext) {
        Intent mIntent = new Intent(Kontext, hwsuebersichtActivity.class);
        mIntent.putExtra(hwsuebersichtActivity.Benutzername_Tag, mBenutzername);
        Kontext.startActivity(mIntent);
    }
}
