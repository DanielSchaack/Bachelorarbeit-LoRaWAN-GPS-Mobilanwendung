package com.project.danielbachelor.datenbank.beziehung;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.project.danielbachelor.datenbank.entitaet.hardwaresystem;
import com.project.danielbachelor.datenbank.entitaet.standort;

import java.util.List;

public class HardwaresystemMitStandort {
    @Embedded
    public hardwaresystem Hardwaresystem;
    @Relation(parentColumn = "HName", entityColumn = "HName")
    public List<standort> Standorte;
}
