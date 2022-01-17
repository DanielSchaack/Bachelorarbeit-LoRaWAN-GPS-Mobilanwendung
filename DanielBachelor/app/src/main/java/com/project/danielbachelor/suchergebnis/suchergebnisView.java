package com.project.danielbachelor.suchergebnis;

import androidx.fragment.app.Fragment;

import com.project.danielbachelor.suche.sucheKontrakt;

public class suchergebnisView extends Fragment implements suchergebnisKontrakt.View {
    private suchergebnisKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(suchergebnisKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
