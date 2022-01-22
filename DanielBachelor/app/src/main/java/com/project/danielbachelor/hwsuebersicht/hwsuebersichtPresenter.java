package com.project.danielbachelor.hwsuebersicht;

import android.content.Context;
import android.content.Intent;

import com.project.danielbachelor.hwshinzufuegen.hwshinzufuegenActivity;

public class hwsuebersichtPresenter implements hwsuebersichtKontrakt.Presenter{
    private final hwsuebersichtView mView;
    private final String mBenutzername;
    public hwsuebersichtPresenter(hwsuebersichtView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void aktiviereHWSHinzufuegen(Context Kontext) {
        Intent mIntent = new Intent(Kontext, hwshinzufuegenActivity.class);
        mIntent.putExtra(hwshinzufuegenActivity.Benutzername_Tag, mBenutzername);
        Kontext.startActivity(mIntent);
    }
}
