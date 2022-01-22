package com.project.danielbachelor.anmeldung;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;

public interface anmeldungKontrakt {
    interface View extends BaseView<Presenter>{
    }

    interface Presenter extends BasePresenter{
        void aktiviereRegistrierungSicht(Context Kontext);
        void aktiviereHauptmenuSicht(Context Kontext, String Benutzername, String Passwort);
    }
}
