package com.project.danielbachelor.suche;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;

public class sucheView extends Fragment implements sucheKontrakt.View {
    private sucheKontrakt.Presenter mPresenter;

    public sucheView() {
    }

    public static sucheView newInstance(){
        sucheView fragment = new sucheView();
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
        View root = inflater.inflate(R.layout.fragment_suche, container, false);
        return root;
    }

    @Override
    public void setPresenter(sucheKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
