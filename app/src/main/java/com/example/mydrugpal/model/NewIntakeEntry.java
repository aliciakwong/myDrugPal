package com.example.mydrugpal.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;


/**
 * holds the data for a new entry to the intake diary. This is not the same as IntakeDiaryItem, this
 * handles adding to the database, and generating the timestamp and id, while IntakeDiaryItem is used
 * to pull the already inputted data from firestore
 * @author Jocelyn MacDonald, Ian Sifton, Richard Purcell
 */
public class NewIntakeEntry implements Serializable {
    /**
     * name of the substance
     */
    public String substanceName;

    /**
     * type of the substance
     */
    public String type;

    /**
     * the number of doses taken relative to the recommended amount per dose
     */
    public String dose;

    /**
     * date of the intake diary when it was created
     */
    public Timestamp dateTime;

    /**
     * The constructor for the IntakeDiaryEntry class
     * @param substanceName the name of the substance being added
     * @param type the type of the substance being added
     * @param dose the number of doses taken relative to the recommended amount per dose as given on
     *             the drugs specific SubstanceDetailActivity page
     */
    public NewIntakeEntry(String substanceName, String type, String dose) {
        this.substanceName = substanceName;
        this.type = type;
        this.dose = dose;
        dateTime = Timestamp.now();
    }

    /**
     * gets the name of the substance
     * @return returns the name of the substance
     */
    public String getNameOfSubstance() {return substanceName;}

    /**
     * gets the type of the substance
     * @return returns the type of substance
     */
    public String getTypeOfSubstance() {return type;}

    /**
     * gets the amount of the substance logged
     * @return returns the amount
     */
    public String getDose() {return dose;}

    /**
     * gets the time of the entry
     * @return returns the time of entry
     */
    public Timestamp getTime() {return dateTime;}

    /**
     * Tests if any of the fields are null
     * @return returns a boolean. Returns true if all the fields are not null. Otherwise returns false
     */
    public boolean NoNullOrEmptyFields()
    {
        return (substanceName != null && substanceName.length() > 0 &&
                type!= null && type.length() > 0 &&
                dose != null && dose.length() > 0);
    }
}
