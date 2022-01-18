package com.project.danielbachelor.hwsuebersicht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.hwshinzufuegen.hwshinzufuegenView;

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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresenter(hwsuebersichtKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
