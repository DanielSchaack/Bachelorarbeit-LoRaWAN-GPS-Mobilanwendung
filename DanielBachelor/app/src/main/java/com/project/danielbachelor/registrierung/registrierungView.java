package com.project.danielbachelor.registrierung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;

public class registrierungView extends Fragment implements registrierungKontrakt.View {
    private registrierungKontrakt.Presenter mPresenter;

    private EditText BenutzernameEingabe;
    private EditText PasswortEingabe;
    private Button Anmeldungbutton;

    public registrierungView() {
    }

    public static registrierungView newInstance(){
        registrierungView fragment = new registrierungView();
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
        View root = inflater.inflate(R.layout.fragment_registrierung, container, false);

        BenutzernameEingabe = root.findViewById(R.id.EnterNameEditText);
        PasswortEingabe = root.findViewById(R.id.EnterPasswortEditText);

        Anmeldungbutton = root.findViewById(R.id.LoginButton);
        Anmeldungbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Benutzername = BenutzernameEingabe.getText().toString();
                String Passwort = PasswortEingabe.getText().toString();
                mPresenter.sendeRegistrierung(Benutzername, Passwort);
            }
        });

        return root;
    }

    @Override
    public void setPresenter(registrierungKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }
}
