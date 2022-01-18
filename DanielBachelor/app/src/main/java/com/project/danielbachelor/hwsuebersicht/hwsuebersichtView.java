package com.project.danielbachelor.hwsuebersicht;

import androidx.fragment.app.Fragment;

public class hwsuebersichtView extends Fragment implements hwsuebersichtKontrakt.View {
    private hwsuebersichtKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(hwsuebersichtKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
