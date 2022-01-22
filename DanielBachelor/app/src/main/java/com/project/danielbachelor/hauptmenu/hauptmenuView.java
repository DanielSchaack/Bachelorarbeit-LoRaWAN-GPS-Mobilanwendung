package com.project.danielbachelor.hauptmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;


public class hauptmenuView extends Fragment implements hauptmenuKontrakt.View {
    private hauptmenuKontrakt.Presenter mPresenter;

    private Button Suchebutton;
    private Button Profilbutton;
    private Button HWSUbersichtbutton;

    public hauptmenuView() {
    }

    public static hauptmenuView newInstance(){
        hauptmenuView fragment = new hauptmenuView();
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
        View root = inflater.inflate(R.layout.fragment_hauptmenu, container, false);

        Suchebutton = root.findViewById(R.id.SucheButton);
        Suchebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.aktiviereSucheSicht(getContext());
            }
        });

        Profilbutton = root.findViewById(R.id.ProfilButton);
        Profilbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.aktiviereProfilSicht(getContext());
            }
        });

        HWSUbersichtbutton = root.findViewById(R.id.HWSUbersichtButton);
        HWSUbersichtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.aktiviereHWSUebersichtSicht(getContext());
            }
        });


        return root;
    }

    @Override
    public void setPresenter(hauptmenuKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
