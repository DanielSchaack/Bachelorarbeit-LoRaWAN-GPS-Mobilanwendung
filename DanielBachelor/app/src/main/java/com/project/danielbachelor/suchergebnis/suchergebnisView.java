package com.project.danielbachelor.suchergebnis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.suche.sucheView;

public class suchergebnisView extends Fragment implements suchergebnisKontrakt.View {
    private suchergebnisKontrakt.Presenter mPresenter;

    public suchergebnisView() {
    }

    public static suchergebnisView newInstance(){
        suchergebnisView fragment = new suchergebnisView();
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
    public void setPresenter(suchergebnisKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
