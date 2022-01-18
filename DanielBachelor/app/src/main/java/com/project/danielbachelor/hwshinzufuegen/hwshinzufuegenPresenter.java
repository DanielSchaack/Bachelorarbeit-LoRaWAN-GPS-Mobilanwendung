package com.project.danielbachelor.hwshinzufuegen;

import com.project.danielbachelor.hauptmenu.hauptmenuView;

public class hwshinzufuegenPresenter implements hwshinzufuegenKontrakt.Presenter{
    private final hwshinzufuegenView mView;
    public hwshinzufuegenPresenter(hwshinzufuegenView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
