package com.project.danielbachelor.hauptmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.anmeldung.anmeldungView;


public class hauptmenuView extends Fragment implements hauptmenuKontrakt.View {
    private hauptmenuKontrakt.Presenter mPresenter;
    public hauptmenuView() {
    }

    public static hauptmenuView newInstance(){
        hauptmenuView fragment = new hauptmenuView();
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
    public void setPresenter(hauptmenuKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
