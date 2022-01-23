package com.project.danielbachelor.hwsuebersicht;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.List;

public interface hwsuebersichtKontrakt {
    interface View extends BaseView<Presenter> {
        void setzeRecViewUndSortierFunktion(List<standort> Liste);

    }

    interface Presenter extends BasePresenter {
        void aktiviereHWSHinzufuegen(Context Kontext);
    }
}
