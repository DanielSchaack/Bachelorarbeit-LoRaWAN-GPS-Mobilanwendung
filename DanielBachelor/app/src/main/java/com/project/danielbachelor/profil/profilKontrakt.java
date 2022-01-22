package com.project.danielbachelor.profil;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;

public interface profilKontrakt {
    interface View extends BaseView<Presenter> {
        void setzeBenutzername(String Benutzername);
    }

    interface Presenter extends BasePresenter {
        void meldeAb(Context Kontext);
    }
}
