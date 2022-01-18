package com.project.danielbachelor.routenergebnis;

import androidx.fragment.app.Fragment;

public class routenergebnisView extends Fragment implements routenergebnisKontrakt.View {
    private routenergebnisKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(routenergebnisKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
