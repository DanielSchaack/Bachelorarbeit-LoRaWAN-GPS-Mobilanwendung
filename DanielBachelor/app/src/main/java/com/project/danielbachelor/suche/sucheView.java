package com.project.danielbachelor.suche;

import androidx.fragment.app.Fragment;

public class sucheView extends Fragment implements sucheKontrakt.View {
    private sucheKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(sucheKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
