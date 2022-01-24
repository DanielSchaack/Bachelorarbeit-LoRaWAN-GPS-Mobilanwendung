package com.project.danielbachelor.hwshinzufuegen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.danielbachelor.HWSHinzuRecAdapter;
import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;
import com.project.danielbachelor.funktionen.Generell;

import java.util.List;

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
        HWSHinzufuegenRecView.setHasFixedSize(true);
        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        HWSHinzufuegenRecView.setLayoutManager(LLM);
        HWSHinzufuegenRecView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        return root;
    }

    @Override
    public void setPresenter(hwshinzufuegenKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }

    @Override
    public void setzeRecView(List<String> Liste) {
        HWSHinzufuegenRecView.setAdapter(new HWSHinzuRecAdapter(Liste, mPresenter));
        HWSHinzufuegenRecView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void zeigeVerknuepfungDialog(String HName) {
        DialogFragment newFragment = new hwshinzufuegenDialog(HName, mPresenter);
        newFragment.show(getParentFragmentManager(), "TAG");
    }
}
