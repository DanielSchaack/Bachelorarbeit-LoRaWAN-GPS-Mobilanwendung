package com.project.danielbachelor.hwshinzufuegen;

import androidx.fragment.app.Fragment;

public class hwshinzufuegenView extends Fragment implements hwshinzufuegenKontrakt.View{
    private hwshinzufuegenKontrakt.Presenter mPresenter;
    @Override
    public void setPresenter(hwshinzufuegenKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
