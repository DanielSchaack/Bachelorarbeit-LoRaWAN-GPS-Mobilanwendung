package com.project.danielbachelor.hwshinzufuegen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.hauptmenu.hauptmenuView;

public class hwshinzufuegenView extends Fragment implements hwshinzufuegenKontrakt.View{
    private hwshinzufuegenKontrakt.Presenter mPresenter;

    public hwshinzufuegenView() {
    }

    public static hwshinzufuegenView newInstance(){
        hwshinzufuegenView fragment = new hwshinzufuegenView();
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
    public void setPresenter(hwshinzufuegenKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
