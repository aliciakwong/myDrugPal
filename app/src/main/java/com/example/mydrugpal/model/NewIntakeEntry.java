package com.example.mydrugpal.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;


/**
 *holds the data for an entry to the intake diary
 * @author Jocelyn MacDonald, Ian Sifton, Richard Purcell
 */
public class NewIntakeEntry implements Serializable {
    public String nameOfSubstance;
    public String typeOfSubstance;
    public String dose;
    public Timestamp time;

    /**
     * The constructor for the IntakeDiaryEntry class
     * @param nameOfSubstance the name of the substance being added
     * @param typeOfSubstance the type of the substance being added
     * @param dose the number of doses taken relative to the recommended amount per dose as given on
     *             the drugs specific SubstanceDetailActivity page
     */
    public NewIntakeEntry(String nameOfSubstance, String typeOfSubstance, String dose) {
        this.nameOfSubstance = nameOfSubstance;
        this.typeOfSubstance = typeOfSubstance;
        this.dose = dose;
        time = Timestamp.now();
    }

    /**
     * gets the name of the substance
     * @return returns the name of the substance
     */
    public String getNameOfSubstance() {return nameOfSubstance;}

    /**
     * gets the type of the substance
     * @return returns the type of substance
     */
    public String getTypeOfSubstance() {return typeOfSubstance;}

    /**
     * gets the amount of the substance logged
     * @return returns the amount
     */
    public String getDose() {return dose;}

    /**
     * gets the time of the entry
     * @return returns the time of entry
     */
    public Timestamp getTime() {return time;}

    /**
     *
     * @return returns a boolean. Returns true if all the fields are not null. Otherwise returns false
     */
    public boolean NoNullOrEmptyFields()
    {
        return (nameOfSubstance != null && nameOfSubstance.length() > 0 &&
                typeOfSubstance!= null && typeOfSubstance.length() > 0 &&
                dose != null && dose.length() > 0);
    }
}
