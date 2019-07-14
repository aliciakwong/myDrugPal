package com.example.mydrugpal.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public void addToDrugs(String email) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference loginCollection = database.collection("Users");
        DocumentReference userRef = loginCollection.document(email);

        for (Map.Entry<String, List<String>> entry : drugs.entrySet()) {
            String id = entry.getKey();
            List<String> fields = entry.getValue();
            String type = fields.get(0);
            String name = fields.get(1);
            String amount = fields.get(2);
            Map<String, String> data = new HashMap<>();
            data.put("substanceName", name);
            data.put("id", id);
            data.put("substanceType", type);
            data.put("amount", amount);


            CollectionReference drugCollection = userRef.collection("drugs");
            drugCollection.document(id).set(data);

        }

    }

}

