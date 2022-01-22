package com.project.danielbachelor.suche;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class sucheView extends Fragment implements sucheKontrakt.View {
    private sucheKontrakt.Presenter mPresenter;

    private Spinner SucheHWSSpinner;
    private EditText SucheDatumEinsEditText;
    private EditText SucheDatumZweiEditText;
    private EditText SucheBGEinsEditText;
    private EditText SucheBGZweiEditText;
    private EditText SucheLGEinsEditText;
    private EditText SucheLGZweiEditText;
    private Button SucheStandorteButton;


    public sucheView() {
    }

    public static sucheView newInstance(){
        sucheView fragment = new sucheView();
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
        View root = inflater.inflate(R.layout.fragment_suche, container, false);

        SucheHWSSpinner = root.findViewById(R.id.SucheHWSSpinner);
        SucheDatumEinsEditText = root.findViewById(R.id.SucheDatumEinsEditText);
        SucheDatumZweiEditText = root.findViewById(R.id.SucheDatumZweiEditText);
        SucheBGEinsEditText = root.findViewById(R.id.SucheBGEinsEditText);
        SucheBGZweiEditText = root.findViewById(R.id.SucheBGZweiEditText);
        SucheLGEinsEditText = root.findViewById(R.id.SucheLGEinsEditText);
        SucheLGZweiEditText = root.findViewById(R.id.SucheLGZweiEditText);
        setzeStandardWerte();

        SucheStandorteButton = root.findViewById(R.id.SucheStandorteButton);
        SucheStandorteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DatumEins = SucheDatumEinsEditText.getText().toString().trim();
                String DatumZwei = SucheDatumZweiEditText.getText().toString().trim();
                String BGEins = SucheBGEinsEditText.getText().toString().trim();
                String BGZwei = SucheBGZweiEditText.getText().toString().trim();
                String LGEins = SucheLGEinsEditText.getText().toString().trim();
                String LGZwei = SucheLGZweiEditText.getText().toString().trim();

                mPresenter.fuehreSucheDurch(getContext(),DatumEins,DatumZwei,BGEins,BGZwei,LGEins,LGZwei);
            }
        });

        return root;
    }

    @Override
    public void setPresenter(sucheKontrakt.Presenter Presenter) {
        mPresenter = Presenter;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setzeStandardWerte() {

        LocalDateTime DateTimeJetzt = LocalDateTime.now();
        LocalDateTime DateTimeAnfangStunde;

        Double GradStandard = 0.0;

        //SucheDatumEinsEditText.setText(DateTimeJetzt.toString());
        //SucheDatumEinsEditText.setText(DateTimeJetzt);
        SucheBGEinsEditText.setText(GradStandard.toString());
        SucheBGZweiEditText.setText(GradStandard.toString());
        SucheLGEinsEditText.setText(GradStandard.toString());
        SucheLGZweiEditText.setText(GradStandard.toString());
    }
}
