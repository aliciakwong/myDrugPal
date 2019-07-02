package com.example.mydrugpal.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold substance information in Firestore database.
 * @author Emma Travers, Richard Purcull, Ian Sifton, Jocelyn MacDonald
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
     * type of substance
     */
    public String substanceType;
    /**
     * amount of the substance logged
     */
    public String amount;

    /**
     * default empty constructor of object
     */
    public InfoPage() {

    }

    /**
     * Method which takes in the name and main info od a substance
     *
     * @param  substanceName name of substance
     * @param substanceType type of substance
     * @param amount the amount of the substance logged or the recommended amount
     *
     */
    public InfoPage(String substanceName, String substanceType, String amount) {
        this.substanceName = substanceName;
        this.substanceType = substanceType;
        this.amount = amount;
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
