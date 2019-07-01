package com.example.mydrugpal.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


/**
 * The class retrieves the data stored for each drug within the map of the drugList
 *
 * @author Jocelyn MacDonald, Richard Purcell
 */
public class MakeIntakeEntry {

    /**
     * The method returns the name of the substance found within the map of drugs
     * @param id the id of the drug in the database, which is also the key of the drug in the map
     * @return the name of the substance
     */
    public static String getSubstanceName(String id){
        if(DrugList.getInstance().getDrugs() != null) {
            System.out.println(id);
            List<String> substanceFields = DrugList.getInstance().getDrugs().get(id);
            System.out.println(substanceFields == null);
            System.out.println(DrugList.getInstance().getDrugs() == null);
            String name = substanceFields.get(1);
            return name;
        }
        else
            return null;
    }

    /**
     * The method returns the type of the substance
     * @param id the id of the drug in the database, which is also the key of the drug in the map
     * @return the type of the substance
     */
    public static String getType(String id) {
        if(DrugList.getInstance().getDrugs() != null) {
            List<String> substanceFields = DrugList.getInstance().getDrugs().get(id);
            String substanceType = substanceFields.get(0);
            return substanceType;
        }
        else
            return null;
    }

    /**
     * The method returns the amount of the substance
     *@param id the id of the drug in the database, which is also the key of the drug in the map
     * @return the amount of the substance
     */
    public static String getAmount(String id) {
        if(DrugList.getInstance().getDrugs() != null) {
            List<String> substanceFields = DrugList.getInstance().getDrugs().get(id);
            String substanceAmount = substanceFields.get(2);
            return substanceAmount;
        }
        else
            return null;
    }


    /**
     *
     * @param id the id of the drug being added into the entry
     * @return the intake diary entry as an IntakeEntryObject
     */
    public static IntakeDiaryEntry makeIntakeEntry(String id) {
        String name = getSubstanceName(id);
        String type = getType(id);
        String amount = getAmount(id);
        IntakeDiaryEntry entry = new IntakeDiaryEntry(name, type, amount);
        return entry;
    }

}