package com.example.mydrugpal.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold substance information in Firestore database.
 */

public class InfoPage implements Serializable {

    public String id;
    public String substanceName;
    public String substanceMainInfo;

    public InfoPage() {

    }

    public InfoPage(String substanceName, String substanceMainInfo) {
        this.substanceName = substanceName;
        this.substanceMainInfo = substanceMainInfo;
    }

    @Override
    public String toString() {
        return "Substance Name: " + substanceName;
    }
}
