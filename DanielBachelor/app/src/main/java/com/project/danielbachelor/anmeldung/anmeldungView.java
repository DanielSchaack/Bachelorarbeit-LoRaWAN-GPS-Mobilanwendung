package com.project.danielbachelor.anmeldung;

import androidx.fragment.app.Fragment;

public class anmeldungView extends Fragment implements anmeldungKontrakt.View {
    private anmeldungKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(anmeldungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
