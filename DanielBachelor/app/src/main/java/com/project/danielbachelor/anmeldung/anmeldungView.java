package com.project.danielbachelor.anmeldung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class anmeldungView extends Fragment implements anmeldungKontrakt.View {
    private anmeldungKontrakt.Presenter mPresenter;

    public anmeldungView() {
    }

    public static anmeldungView newInstance(){
        anmeldungView fragment = new anmeldungView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresenter(anmeldungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
