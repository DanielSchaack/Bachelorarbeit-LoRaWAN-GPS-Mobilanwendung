package com.project.danielbachelor.registrierung;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;

public interface registrierungKontrakt {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void sendeRegistrierung(Context Kontext, String Benutzername, String Passwort);
    }
}
