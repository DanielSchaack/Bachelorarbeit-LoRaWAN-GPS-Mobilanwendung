package com.project.danielbachelor.hwsuebersicht;

import android.content.Context;

import com.project.danielbachelor.BasePresenter;
import com.project.danielbachelor.BaseView;

public interface hwsuebersichtKontrakt {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void aktiviereHWSHinzufuegen(Context Kontext);
    }
}
