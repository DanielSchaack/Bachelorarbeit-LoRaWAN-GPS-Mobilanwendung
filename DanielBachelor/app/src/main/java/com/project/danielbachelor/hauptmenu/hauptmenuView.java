package com.project.danielbachelor.hauptmenu;

import androidx.fragment.app.Fragment;


public class hauptmenuView extends Fragment implements hauptmenuKontrakt.View {
    private hauptmenuKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(hauptmenuKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
