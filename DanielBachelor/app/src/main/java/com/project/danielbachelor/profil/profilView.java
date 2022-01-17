package com.project.danielbachelor.profil;

import androidx.fragment.app.Fragment;

import com.project.danielbachelor.hwsuebersicht.hwsuebersichtKontrakt;

public class profilView extends Fragment implements profilKontrakt.View {
    private profilKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(profilKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
