package com.project.danielbachelor.profil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.hwsuebersicht.hwsuebersichtView;

public class profilView extends Fragment implements profilKontrakt.View {
    private profilKontrakt.Presenter mPresenter;

    public profilView() {
    }

    public static profilView newInstance(){
        profilView fragment = new profilView();
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
    public void setPresenter(profilKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
