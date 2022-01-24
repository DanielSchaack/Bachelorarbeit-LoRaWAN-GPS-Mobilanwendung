package com.project.danielbachelor.hwshinzufuegen;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.List;

public interface hwshinzufuegenKontrakt {
    interface View extends BaseView<Presenter> {
        void setzeRecView(List<String> Liste);
        void zeigeVerknuepfungDialog(String HName);

    }

    interface Presenter extends BasePresenter {
        void oeffneDialogZumVerknuepfen(String HName);
        void verknuepfeHardwaresystem(Context Kontext, String HName, String Passwort);

    }
}
