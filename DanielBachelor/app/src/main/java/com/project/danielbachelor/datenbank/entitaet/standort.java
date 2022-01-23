package com.project.danielbachelor.datenbank.entitaet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "standort")
public class standort {
    @PrimaryKey(autoGenerate = true)
    private final int SID;

    private final Timestamp SErfassungszeit;
    private final double SBreitengrad, SLaengengrad;

    //foreign key
    private final String HName;

    public standort(int SID, Timestamp SErfassungszeit, double SBreitengrad, double SLaengengrad, String HName) {
        this.SID = SID;
        this.SErfassungszeit = SErfassungszeit;
        this.SBreitengrad = SBreitengrad;
        this.SLaengengrad = SLaengengrad;
        this.HName = HName;
    }

    public int getSID() {
        return SID;
    }

    public Timestamp getSErfassungszeit() {
        return SErfassungszeit;
    }

    public double getSBreitengrad() {
        return SBreitengrad;
    }

    public double getSLaengengrad() {
        return SLaengengrad;
    }

    public String getHName() {
        return HName;
    }
}
