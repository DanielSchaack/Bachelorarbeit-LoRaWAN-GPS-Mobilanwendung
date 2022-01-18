package com.project.danielbachelor.hwsuebersicht;

import com.project.danielbachelor.hwshinzufuegen.hwshinzufuegenView;

public class hwsuebersichtPresenter implements hwsuebersichtKontrakt.Presenter{
    private final hwsuebersichtView mView;
    public hwsuebersichtPresenter(hwsuebersichtView AV) {
        this.mView = AV;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
