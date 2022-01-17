package com.project.danielbachelor.datenbank.entitaet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "hardwaresystem")
public class hardwaresystem {
    @PrimaryKey
    private final String HName;

    private final String HPasswort;
    private final Timestamp HRegDatum;

    //foreign key
    private final String BName;

    public hardwaresystem(String HName, String HPasswort, Timestamp HRegDatum, String BName) {
        this.HName = HName;
        this.HPasswort = HPasswort;
        this.HRegDatum = HRegDatum;
        this.BName = BName;
    }

    public String getHName() {
        return HName;
    }

    public String getHPasswort() {
        return HPasswort;
    }

    public Timestamp getHRegDatum() {
        return HRegDatum;
    }

    public String getBName() {
        return BName;
    }
}
