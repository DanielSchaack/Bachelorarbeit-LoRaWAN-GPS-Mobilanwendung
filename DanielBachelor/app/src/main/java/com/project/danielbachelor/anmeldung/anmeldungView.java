package com.project.danielbachelor.anmeldung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;

public class anmeldungView extends Fragment implements anmeldungKontrakt.View {
    private anmeldungKontrakt.Presenter mPresenter;

    private EditText BenutzernameEingabe;
    private EditText PasswortEingabe;
    private Button Anmeldungbutton;
    private Button Registrierungsbutton;
    public anmeldungView() {
    }

    public static anmeldungView newInstance(){
        anmeldungView fragment = new anmeldungView();
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
        View root = inflater.inflate(R.layout.fragment_anmeldung, container, false);

        BenutzernameEingabe = root.findViewById(R.id.EnterNameEditText);
        PasswortEingabe = root.findViewById(R.id.EnterPasswortEditText);

        Registrierungsbutton = root.findViewById(R.id.RegButton);
        Registrierungsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.aktiviereRegistrierungSicht(getContext());
            }
        });

        Anmeldungbutton = root.findViewById(R.id.LoginButton);
        Anmeldungbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Benutzername = BenutzernameEingabe.getText().toString().trim();
                String Passwort = PasswortEingabe.getText().toString().trim();
                mPresenter.aktiviereHauptmenuSicht(getContext(), Benutzername, Passwort);
            }
        });
        return root;
    }

    @Override
    public void setPresenter(anmeldungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }

}
