package com.example.mydrugpal.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;


/**
 *holds the data for an entry to the intake diary
 * @author Jocelyn MacDonald, Ian Sifton
 */
public class IntakeDiaryEntry implements Serializable {
    public String nameOfSubstance;
    public String typeOfSubstance;
    public int amount;
    public Timestamp time;

    /**
     * The constructor for the IntakeDiaryEntry class
     * @param nameOfSubstance the name of the substance being added
     * @param typeOfSubstance the type of the substance being added
     * @param amount the amount of the substance taken
     */
    public IntakeDiaryEntry(String nameOfSubstance, String typeOfSubstance, int amount) {
        this.nameOfSubstance = nameOfSubstance;
        this.typeOfSubstance = typeOfSubstance;
        this.amount = amount;
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
    public int getAmount() {return amount;}

    /**
     * gets the time of the entry
     * @return returns the time of entry
     */
    public Timestamp getTime() {return time;}
}
