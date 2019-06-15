package com.example.mydrugpal.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold substance information in Firestore database.
 * @author Emma Travers, Richard Purcull, Ian Sifton
 *
 */

public class InfoPage implements Serializable {

    public String id;
    public String substanceName;
    public String substanceMainInfo;

    public InfoPage() {

    }

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param substanceName
     * @param substanceMainInfo
     *
     * Method which takes in the name and main info od a substance
     */
    public InfoPage(String substanceName, String substanceMainInfo) {
        this.substanceName = substanceName;
        this.substanceMainInfo = substanceMainInfo;
    }

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @return String with substance name
     *
     * Method which returns the substance name as a String
     */
    @Override
    public String toString() {
        return "Substance Name: " + substanceName;
    }
}
