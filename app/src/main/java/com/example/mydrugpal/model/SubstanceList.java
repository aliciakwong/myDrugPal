package com.example.mydrugpal.model;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton implementation of DrugList that holds current drugs from database
 * @author Jocelyn MacDonald, Alicia Wong
 */
public class SubstanceList {
    private static volatile SubstanceList instance;
    private Map<String, List<String>> drugs;

    private SubstanceList(){

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

    public static SubstanceList getInstance(){
        if (instance == null) {
            synchronized (SubstanceList.class) {
                if (instance == null) {
                    instance = new SubstanceList();
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

    public void updateSubstances(List<DocumentSnapshot> updatedList){
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
            for(int i=0; i < fields.size();i++) {
                System.out.println(fields.get(i));
            }
            drugs.put(id,fields);
        }

    }

    /**
     * method returns users in the drugList instance
     * @return HashMap of the drugs and associated information
     */
    public Map<String, List<String>> getSubstances() {
        return drugs;
    }
}