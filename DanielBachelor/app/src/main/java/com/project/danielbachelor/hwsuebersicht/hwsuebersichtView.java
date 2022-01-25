package com.project.danielbachelor.hwsuebersicht;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;

import java.util.List;

public class hwsuebersichtView extends Fragment implements hwsuebersichtKontrakt.View {
    private hwsuebersichtKontrakt.Presenter mPresenter;

    private Button SortierBGButton;
    private Button SortierLGButton;
    private Button SortierZeitButton;
    private RecyclerView HWSUebersichtRecView;
    private FloatingActionButton HWSUebersichtFAB;

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

        SortierBGButton = root.findViewById(R.id.SortierBGButton);
        SortierLGButton = root.findViewById(R.id.SortierLGButton);
        SortierZeitButton = root.findViewById(R.id.SortierZeitButton);

        HWSUebersichtRecView = root.findViewById(R.id.HWSUebersichtRecView);
        HWSUebersichtRecView.setHasFixedSize(true);
        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        HWSUebersichtRecView.setLayoutManager(LLM);
        HWSUebersichtRecView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        HWSUebersichtFAB = root.findViewById(R.id.HWSUebersichtFAB);
        HWSUebersichtFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.aktiviereHWSHinzufuegen(getContext());
            }
        });
        return root;
    }

    @Override
    public void setPresenter(hwsuebersichtKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }

    @Override
    public void setzeRecViewUndSortierFunktion(List<standort> Liste) {
        Generell.fuegeSortierFunktionZuButtonVonRecView(Liste, SortierBGButton, SortierLGButton, SortierZeitButton, HWSUebersichtRecView);
    }
}
