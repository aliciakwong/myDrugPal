package com.example.mydrugpal;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SubstanceSummaryInformation {
    private List<IntakeDiaryItem> substanceList;

    public SubstanceSummaryInformation() {
        substanceList = new ArrayList<>();
    }

    List<IntakeDiaryItem> getSubstanceList() {
        return substanceList;
    }

    public void updateSubstanceList(List<DocumentSnapshot> intakeDiary){
        substanceList = new ArrayList<>();

        for(DocumentSnapshot d: intakeDiary){
            String id = d.getId();
            String dateTime = d.get("dateTime").toString();
            String dose = d.get("dose").toString();
            String substanceName = d.get("substanceName").toString();
            String type = d.get("type").toString();

            IntakeDiaryItem currentItem = new IntakeDiaryItem(id, substanceName, type, dose, dateTime);
            substanceList.add(currentItem);
        }


    }

}




