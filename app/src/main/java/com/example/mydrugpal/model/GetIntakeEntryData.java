package com.example.mydrugpal.model;


import java.util.List;


/**
 * The class retrieves the data stored for each drug within the map of the drugList
 * @author Jocelyn MacDonald, Richard Purcell
 */
public class GetIntakeEntryData {

    /**
     * The method returns the name of the substance found within the map of drugs
     * @param id the id of the drug in the database, which is also the key of the drug in the map
     * @return the name of the substance
     */
    public static String getSubstanceName(String id) {
        if (DrugList.getInstance().getDrugs() != null) {
            List<String> substanceFields = DrugList.getInstance().getDrugs().get(id);
            String name = substanceFields.get(1);
            return name;
        } else
            return null;
    }

    /**
     * The method returns the type of the substance
     * @param id the id of the drug in the database, which is also the key of the drug in the map
     * @return the type of the substance
     */
    public static String getType(String id) {
        if (DrugList.getInstance().getDrugs() != null) {
            List<String> substanceFields = DrugList.getInstance().getDrugs().get(id);
            String substanceType = substanceFields.get(0);
            return substanceType;
        } else
            return null;
    }

    /**
     * The method returns the amount of the substance
     * @param id the id of the drug in the database, which is also the key of the drug in the map
     * @return the amount of the substance
     */
    public static String getAmount(String id) {
        if (DrugList.getInstance().getDrugs() != null) {
            List<String> substanceFields = DrugList.getInstance().getDrugs().get(id);
            String substanceAmount = substanceFields.get(2);
            return substanceAmount;
        } else
            return null;
    }
}

