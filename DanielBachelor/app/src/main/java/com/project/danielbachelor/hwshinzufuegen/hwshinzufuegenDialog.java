package com.project.danielbachelor.hwshinzufuegen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.project.danielbachelor.R;

public class hwshinzufuegenDialog extends DialogFragment {
    private String HName;
    private hwshinzufuegenKontrakt.Presenter mPresenter;

    public hwshinzufuegenDialog(String HName, hwshinzufuegenKontrakt.Presenter mPresenter) {
        this.HName = HName;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_hwshinzufuegen, null)).setPositiveButton(R.string.registrieren, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText PasswortET = (EditText) hwshinzufuegenDialog.this.getDialog().findViewById(R.id.dialogHWSPasswortEditView);
                String Passwort = PasswortET.getText().toString().trim();

                mPresenter.verknuepfeHardwaresystem(getContext(), HName, Passwort);
            }
        }).setNegativeButton(R.string.abbrechen, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                hwshinzufuegenDialog.this.getDialog().cancel();
            }
        });
        builder.setTitle(R.string.HWSPasswortEingeben);
        return builder.create();
    }
}
