package com.project.danielbachelor.routenergebnis;

import androidx.fragment.app.Fragment;

import com.project.danielbachelor.registrierung.registrierungKontrakt;

public class routenergebnisView extends Fragment implements routenergebnisKontrakt.View {
    private routenergebnisKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(routenergebnisKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
