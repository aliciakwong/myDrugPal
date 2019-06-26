package com.example.mydrugpal.model;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton implementation of DrugList that holds current drugs from database
 * @author Jocelyn MacDonald, Ian Sifton
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
     * method updates drugs hashMap to contain updated list from database
     * @param updatedList list of documentSnapshots from database
     * @see <a href="https://dzone.com/articles/hashmap-%E2%80%93-single-key-and"></a>
     */

    public void updateDrugs(List<DocumentSnapshot> updatedList){
        drugs = new HashMap<String, List<String>>();

        for(DocumentSnapshot d: updatedList){
            List<String> fields = new ArrayList<>();
            String id = d.getId();
            String substanceMainInfo = d.get("substanceMainInfo").toString();
            String substanceName = d.get("substanceName").toString();
            fields.add(substanceMainInfo);
            fields.add(substanceName);
            drugs.put(id,fields);
        }

    }

    /**
     * method returns users in the UserList instance
     * @return HashMap of the drugs and associated information
     */
    public Map<String, List<String>> getDrugs() {
        return drugs;
    }
}
