package com.project.danielbachelor.hwshinzufuegen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.project.danielbachelor.R;

public class hwshinzufuegenView extends Fragment implements hwshinzufuegenKontrakt.View{
    private hwshinzufuegenKontrakt.Presenter mPresenter;

    private RecyclerView HWSHinzufuegenRecView;

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
        View root = inflater.inflate(R.layout.fragment_hwshinzufuegen, container, false);

        HWSHinzufuegenRecView = root.findViewById(R.id.HWSHinzufuegenRecView);

        return root;
    }

    @Override
    public void setPresenter(hwshinzufuegenKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
