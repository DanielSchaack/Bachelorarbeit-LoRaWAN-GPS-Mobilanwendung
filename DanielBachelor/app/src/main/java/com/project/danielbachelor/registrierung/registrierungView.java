package com.project.danielbachelor.registrierung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.profil.profilView;

public class registrierungView extends Fragment implements registrierungKontrakt.View {
    private registrierungKontrakt.Presenter mPresenter;

    public registrierungView() {
    }

    public static registrierungView newInstance(){
        registrierungView fragment = new registrierungView();
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
    public void setPresenter(registrierungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
