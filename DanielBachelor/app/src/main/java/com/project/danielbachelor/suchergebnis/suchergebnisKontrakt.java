package com.project.danielbachelor.suchergebnis;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.List;

public interface suchergebnisKontrakt {
    interface ListeView extends BaseView<ListePresenter> {
        void setzeRecViewUndSortierButtons(List<standort> mStandortListe);
    }

    interface ListePresenter extends BasePresenter {
    }

    interface KarteView extends BaseView<KartePresenter> {
        void setzeGeoPoints(List<standort> mStandortListe);
    }

    interface KartePresenter extends BasePresenter {
    }
}
