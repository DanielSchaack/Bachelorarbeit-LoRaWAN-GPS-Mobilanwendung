package com.project.danielbachelor.suche;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.time.LocalDateTime;
import java.util.List;

public interface sucheKontrakt {
    interface View extends BaseView<Presenter> {
        void setzeStandardWerte();
        void setzeSpinner(List<standort> StandortListe);
        void setzeEditText(android.view.View view, LocalDateTime dateTime);
    }

    interface Presenter extends BasePresenter {
        void fuehreSucheDurch(Context Kontext, String HName, String DatumEins, String DatumZwei,
                                                String BGEins, String BGZwei,
                                                String LGEins, String LGZwei);
        void fuehreDateTimePickerDurch(android.view.View view);
    }
}
