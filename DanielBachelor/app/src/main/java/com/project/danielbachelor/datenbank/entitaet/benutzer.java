package com.project.danielbachelor.datenbank.entitaet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "benutzer")
public class benutzer {
    @PrimaryKey
    private final String BName;

    private final String BPasswort;
    private final String BToken;

    public benutzer(String BName, String BPasswort, String BToken) {
        this.BName = BName;
        this.BPasswort = BPasswort;
        this.BToken = BToken;
    }

    public String getBToken() {
        return BToken;
    }

    public String getBName() {
        return BName;
    }

    public String getBPasswort() {
        return BPasswort;
    }
}
