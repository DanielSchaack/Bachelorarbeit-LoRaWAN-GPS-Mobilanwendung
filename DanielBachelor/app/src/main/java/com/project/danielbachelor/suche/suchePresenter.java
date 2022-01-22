package com.project.danielbachelor.suche;

import android.content.Context;
import android.content.Intent;

import com.project.danielbachelor.hwsuebersicht.hwsuebersichtActivity;
import com.project.danielbachelor.suchergebnis.suchergebnisActivity;

public class suchePresenter implements sucheKontrakt.Presenter{
    private final sucheView mView;
    private final String mBenutzername;
    public suchePresenter(sucheView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void fuehreSucheDurch(Context Kontext, String DatumEins, String DatumZwei, String BGEins, String BGZwei, String LGEins, String LGZwei) {

        Intent mIntent = new Intent(Kontext, suchergebnisActivity.class);
        mIntent.putExtra(suchergebnisActivity.Benutzername_Tag, mBenutzername);
        Kontext.startActivity(mIntent);
    }
}
