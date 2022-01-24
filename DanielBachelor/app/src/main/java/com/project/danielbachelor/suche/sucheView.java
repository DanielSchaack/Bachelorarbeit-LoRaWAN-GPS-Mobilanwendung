package com.project.danielbachelor.suche;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.danielbachelor.R;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        SucheDatumEinsEditText.setFocusable(false);
        SucheDatumEinsEditText.setInputType(InputType.TYPE_NULL);
        SucheDatumEinsEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.fuehreDateTimePickerDurch(view);
            }
        });


        SucheDatumZweiEditText = root.findViewById(R.id.SucheDatumZweiEditText);
        SucheDatumZweiEditText.setFocusable(false);
        SucheDatumZweiEditText.setInputType(InputType.TYPE_NULL);
        SucheDatumZweiEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.fuehreDateTimePickerDurch(view);
            }
        });

        SucheBGEinsEditText = root.findViewById(R.id.SucheBGEinsEditText);
        SucheBGZweiEditText = root.findViewById(R.id.SucheBGZweiEditText);
        SucheLGEinsEditText = root.findViewById(R.id.SucheLGEinsEditText);
        SucheLGZweiEditText = root.findViewById(R.id.SucheLGZweiEditText);
        setzeStandardWerte();

        SucheStandorteButton = root.findViewById(R.id.SucheStandorteButton);
        SucheStandorteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String HName = SucheHWSSpinner.getSelectedItem().toString().trim();
                String DatumEins = SucheDatumEinsEditText.getText().toString().trim();
                String DatumZwei = SucheDatumZweiEditText.getText().toString().trim();
                String BGEins = SucheBGEinsEditText.getText().toString().trim();
                String BGZwei = SucheBGZweiEditText.getText().toString().trim();
                String LGEins = SucheLGEinsEditText.getText().toString().trim();
                String LGZwei = SucheLGZweiEditText.getText().toString().trim();

                mPresenter.fuehreSucheDurch(getContext(),HName, DatumEins,DatumZwei,BGEins,BGZwei,LGEins,LGZwei);
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime DateTimeJetzt = LocalDateTime.now();
        LocalDateTime DateTimeAnfangStunde = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        double GradStandard = 0.0;

        SucheDatumEinsEditText.setText(dtf.format(DateTimeJetzt));
        SucheDatumZweiEditText.setText(dtf.format(DateTimeAnfangStunde));
        SucheBGEinsEditText.setText(Double.toString(GradStandard));
        SucheBGZweiEditText.setText(Double.toString(GradStandard));
        SucheLGEinsEditText.setText(Double.toString(GradStandard));
        SucheLGZweiEditText.setText(Double.toString(GradStandard));
    }

    @Override
    public void setzeSpinner(List<standort> StandortListe) {
        String[] HNameArray = new String[StandortListe.size()];
        for (int i = 0; i < StandortListe.size(); i++) {
            HNameArray[i] = StandortListe.get(i).getHName();
        }

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,HNameArray);
        SucheHWSSpinner.setAdapter(mAdapter);
    }

    @Override
    public void setzeEditText(View view, LocalDateTime dateTime) {
        EditText mEditText = (EditText) view;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        mEditText.setText(dtf.format(dateTime));
    }
}
