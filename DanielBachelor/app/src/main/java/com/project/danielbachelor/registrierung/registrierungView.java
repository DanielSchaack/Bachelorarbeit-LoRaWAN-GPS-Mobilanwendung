package com.project.danielbachelor.registrierung;

import androidx.fragment.app.Fragment;

public class registrierungView extends Fragment implements registrierungKontrakt.View {
    private registrierungKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(registrierungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
