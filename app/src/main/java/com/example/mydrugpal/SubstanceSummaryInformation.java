package com.example.mydrugpal;

import com.example.mydrugpal.model.IntakeDiaryItem;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Additional information for substance summary class. Holds
 * substance list.
 *
 * @author Megan Brock, Alicia Wong.
 */
public class SubstanceSummaryInformation {
    private List<IntakeDiaryItem> substanceList;

    public SubstanceSummaryInformation() {
        substanceList = new ArrayList<>();
    }

    List<IntakeDiaryItem> getSubstanceList() {
        return substanceList;
    }

    /**
     * Updates the substance list to contain entries
     * from FireStore bewteeen start and end dates.
     * @param intakeDiary
     */
    public void updateSubstanceList(List<DocumentSnapshot> intakeDiary){
        substanceList = new ArrayList<>();

        for(DocumentSnapshot d: intakeDiary){
            String id = d.getId();

            Timestamp dateTime = ((Timestamp)d.get("dateTime"));
            String dose = d.get("dose").toString();
            String substanceName = d.get("substanceName").toString();
            String type = d.get("type").toString();
            IntakeDiaryItem currentItem = new IntakeDiaryItem(id, substanceName, type, dose, dateTime);
            substanceList.add(currentItem);
        }
    }

    /**
     * Parse date from a string.
     * @param date String to parse, YYYY-MM-DD
     * @return Parsed date or null
     */
    public static Date parseDate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d = null;

        try
        {
            d = sdf.parse(date);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return d;
    }

    /**
     * Parse date from a size 3 integer array.
     * @param date Integer array to parse, YYYY-MM-DD
     * @return Parsed date or null
     */
    public static Date parseDate(int[] date)
    {
        if (date == null) return null;

        String dateString = date[0] + "-" + date[1] + "-" + date[2];

        return parseDate(dateString);
    }
}




