package com.project.danielbachelor.suche;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;

public interface sucheKontrakt {
    interface View extends BaseView<Presenter> {
        void setzeStandardWerte();
    }

    interface Presenter extends BasePresenter {
        void fuehreSucheDurch(Context Kontext, String DatumEins, String DatumZwei,
                                                String BGEins, String BGZwei,
                                                String LGEins, String LGZwei);
    }
}
