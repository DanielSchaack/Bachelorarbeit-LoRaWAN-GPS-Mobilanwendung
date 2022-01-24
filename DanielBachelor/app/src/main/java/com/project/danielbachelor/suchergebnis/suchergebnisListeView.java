package com.project.danielbachelor.suchergebnis;

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

public class suchergebnisListeView extends Fragment implements suchergebnisKontrakt.ListeView {
    private suchergebnisKontrakt.ListePresenter mPresenter;

    private Button SortierBGButton;
    private Button SortierLGButton;
    private Button SortierZeitButton;
    private RecyclerView SuchergebnisUebersichtRecView;
    private FloatingActionButton SuchergebnisListeFAB;

    public suchergebnisListeView() {
    }

    public static suchergebnisListeView newInstance(){
        suchergebnisListeView fragment = new suchergebnisListeView();
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
        View root = inflater.inflate(R.layout.fragment_suchergebnis_liste, container, false);

        SortierBGButton = root.findViewById(R.id.SortierBGButton);
        SortierLGButton = root.findViewById(R.id.SortierLGButton);
        SortierZeitButton = root.findViewById(R.id.SortierZeitButton);

        SuchergebnisUebersichtRecView = root.findViewById(R.id.SuchergebnisRecView);
        SuchergebnisUebersichtRecView.setHasFixedSize(true);
        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        SuchergebnisUebersichtRecView.setLayoutManager(LLM);
        SuchergebnisUebersichtRecView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        SuchergebnisListeFAB = root.findViewById(R.id.SuchergebnisFAB);
        SuchergebnisListeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.fuehreRoutensucheDurch(getContext());
            }
        });
        return root;
    }

    @Override
    public void setPresenter(suchergebnisKontrakt.ListePresenter Presenter) {
        mPresenter = Presenter;
    }

    @Override
    public void setzeRecViewUndSortierButtons(List<standort> mStandortListe) {
        Generell.fuegeSortierFunktionZuButtonVonRecView(mStandortListe, SortierBGButton, SortierLGButton, SortierZeitButton, SuchergebnisUebersichtRecView);
    }
}
