package com.example.mydrugpal.model;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton implementation of DrugList that holds current drugs from database
 * @author Jocelyn MacDonald, Ian Sifton, Richard Purcell
 */
public class DrugList {
    private static volatile DrugList instance;
    private Map<String, List<String>> drugs;

    private DrugList(){

    }

    /**
     * Method to get the one static instance of druglist.
     * If drugList is not created yet, it will create the one instance
     *
     * @return single static instance of DrugList object
     *
     * @see <a href="https://sourcemaking.com/design_patterns/singleton/java/2"></a>
     *
     */

    public static DrugList getInstance(){
        if (instance == null) {
            synchronized (DrugList.class) {
                if (instance == null) {
                    instance = new DrugList();
                }
            }
        }
        return instance;
    }

    /**
     * method updates drugs hashMap to contain updated list from database.
     * The id of the database entry is used as the key in the hashmap. The substance name, type
     * and amount are taken from the snapshot and placed into a list. The list is the corresponding
     * value in the map to the entry id.
     * @param updatedList list of documentSnapshots from drugs database
     * @see <a href="https://dzone.com/articles/hashmap-%E2%80%93-single-key-and"></a>
     */

    public void updateDrugs(List<DocumentSnapshot> updatedList){
        drugs = new HashMap<String, List<String>>();

        for(DocumentSnapshot d: updatedList){
            List<String> fields = new ArrayList<>();
            String id = d.getId();
            String substanceType = d.get("substanceType").toString();
            String substanceName = d.get("substanceName").toString();
            String amount = d.get("amount").toString();
            fields.add(substanceType);
            fields.add(substanceName);
            fields.add(amount);
            drugs.put(id,fields);
        }

    }

    /**
     * method returns drugs in the drugList instance
     * @return HashMap of the drugs and associated information
     */
    public Map<String, List<String>> getDrugs() {
        return drugs;
    }
}
