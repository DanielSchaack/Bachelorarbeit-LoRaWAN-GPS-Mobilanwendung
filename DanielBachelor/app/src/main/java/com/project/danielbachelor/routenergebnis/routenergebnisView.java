package com.project.danielbachelor.routenergebnis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.registrierung.registrierungView;

public class routenergebnisView extends Fragment implements routenergebnisKontrakt.View {
    private routenergebnisKontrakt.Presenter mPresenter;

    public routenergebnisView() {
    }

    public static routenergebnisView newInstance(){
        routenergebnisView fragment = new routenergebnisView();
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
    public void setPresenter(routenergebnisKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
