package com.project.danielbachelor.registrierung;

import androidx.fragment.app.Fragment;

import com.project.danielbachelor.profil.profilKontrakt;

public class registrierungView extends Fragment implements registrierungKontrakt.View {
    private registrierungKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(registrierungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
