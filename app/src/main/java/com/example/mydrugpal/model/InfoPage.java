package com.example.mydrugpal.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold substance information in Firestore database.
 * @author Emma Travers, Richard Purcull, Ian Sifton
 *
 */

public class InfoPage implements Serializable {

    /**
     * id from database
     */
    public String id;
    /**
     * name of substance
     */
    public String substanceName;
    /**
     * information about substance
     */
    public String substanceMainInfo;

    /**
     * default empty constructor of object
     */
    public InfoPage() {

    }

    /**
     * Method which takes in the name and main info od a substance
     *
     * @param  substanceName name of substance
     * @param substanceMainInfo main information of substance
     *
     */
    public InfoPage(String substanceName, String substanceMainInfo) {
        this.substanceName = substanceName;
        this.substanceMainInfo = substanceMainInfo;
    }

    /**
     * Method which returns the substance name as a String
     *
     * @return String with substance name
     *
     */
    @Override
    public String toString() {
        return "Substance Name: " + substanceName;
    }
}
