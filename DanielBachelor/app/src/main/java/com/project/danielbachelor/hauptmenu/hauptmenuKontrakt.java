package com.project.danielbachelor.hauptmenu;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;

public interface hauptmenuKontrakt {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void aktiviereSucheSicht(Context Kontext);
        void aktiviereProfilSicht(Context Kontext);
        void aktiviereHWSUebersichtSicht(Context Kontext);
    }
}
