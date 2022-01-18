package com.project.danielbachelor.hwsuebersicht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;

public class hwsuebersichtView extends Fragment implements hwsuebersichtKontrakt.View {
    private hwsuebersichtKontrakt.Presenter mPresenter;

    public hwsuebersichtView() {
    }

    public static hwsuebersichtView newInstance(){
        hwsuebersichtView fragment = new hwsuebersichtView();
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
        View root = inflater.inflate(R.layout.fragment_hwsuebersicht, container, false);
        return root;
    }

    @Override
    public void setPresenter(hwsuebersichtKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
