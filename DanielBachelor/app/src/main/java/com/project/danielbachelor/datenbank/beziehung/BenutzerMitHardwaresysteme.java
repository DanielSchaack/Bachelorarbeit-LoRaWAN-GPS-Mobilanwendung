package com.project.danielbachelor.datenbank.beziehung;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.project.danielbachelor.datenbank.entitaet.benutzer;
import com.project.danielbachelor.datenbank.entitaet.hardwaresystem;

import java.util.List;

public class BenutzerMitHardwaresysteme {
    @Embedded
    public benutzer Benutzer;
    @Relation(parentColumn = "BName", entityColumn = "BName")
    public List<hardwaresystem> Hardwaresysteme;
}
