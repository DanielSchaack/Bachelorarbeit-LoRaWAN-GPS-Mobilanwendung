package com.project.danielbachelor.hwshinzufuegen;

public class hwshinzufuegenPresenter implements hwshinzufuegenKontrakt.Presenter{
    private final hwshinzufuegenView mView;
    private final String mBenutzername;
    public hwshinzufuegenPresenter(hwshinzufuegenView AV, String Benutzername) {
        this.mView = AV;
        this.mBenutzername = Benutzername;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
