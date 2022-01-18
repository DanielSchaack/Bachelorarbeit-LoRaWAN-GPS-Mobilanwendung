package com.project.danielbachelor.hwshinzufuegen;

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
