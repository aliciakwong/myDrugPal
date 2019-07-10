package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.EditIntakeDiaryEntry;
import com.example.mydrugpal.model.IntakeDiaryItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;

/**
 * Activity for the substance summary diary page. Contains buttons for
 * choosing start and end date on calendars. Substances consumed between
 * the two dates are shown in the summary list.
 *
 * @author Megan Brock, Richard Purcell, Alicia Wong, Ian Sifton
 */
public class SubstanceSummaryActivity extends LogoutActivity
{
    private int[] startDate;
    private int[] endDate;

    public Button dateSelectButton;

    public Button startDateButton;
    public Button endDateButton;

    public DatePicker startDatePicker;
    public DatePicker endDatePicker;

    private ScrollView scrollView;
    private LinearLayout scrollViewLayout;

    private SubstanceSummaryInformation summaryInformation;

    public TabLayout layout;
    public TabLayout.Tab list;
    public TabLayout.Tab about;
    public TabLayout.Tab diary;

    /**
     * Finds references to UI elements. Adds listeners
     * to button onClick and calendar onSelectedDayChange
     * events.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        getLayoutInflater().inflate(R.layout.activity_substance_summary, contentFrameLayout);

        summaryInformation = new SubstanceSummaryInformation();

        layout = findViewById(R.id.menuTabLayout);
        list = layout.getTabAt(0);
        diary = layout.getTabAt(1);
        about = layout.getTabAt(2);

        diary.select();

        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTab(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // no action here, needs override definition
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // no action here, needs override definition
            }
        });

        startDate = new int[3];
        endDate = new int[3];

        dateSelectButton = findViewById(R.id.selectDateRangeButton);

        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);

        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);

        scrollView = findViewById(R.id.scrollView);
        scrollViewLayout = findViewById(R.id.scrollViewLayout);

        dateSelectButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             * On click method for when dates are selected
             * @param v view of activity
             */
            public void onClick(View v)
            {
                toggleButtons();
            }
        });

        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(startDatePicker, endDatePicker);
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(endDatePicker, startDatePicker);
            }
        });

        startDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                changeStartDate(year, month, dayOfMonth);
                startDateButton.setText(month+1 + " " + dayOfMonth + " " + year);
            }
        });

        endDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                changeEndDate(year, month, dayOfMonth);
                endDateButton.setText(month+1 + " " + dayOfMonth + " " + year);
            }
        });

        startDateButton.setVisibility(View.INVISIBLE);
        endDateButton.setVisibility(View.INVISIBLE);

        startDatePicker.setVisibility(View.INVISIBLE);
        endDatePicker.setVisibility(View.INVISIBLE);

        scrollView.setVisibility(View.VISIBLE);

        if (CurrentUser.getInstance() != null && CurrentUser.getInstance().GetEmail() != null && CurrentUser.getInstance().GetEmail() != "") {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            DocumentReference userDocument = database.collection("Users").
                    document(CurrentUser.getInstance().GetEmail());
            CollectionReference userIntakeDiary = userDocument.collection("IntakeDiary");
            Query diaryByDate = userIntakeDiary.orderBy("dateTime");
            diaryByDate.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                /**
                 * method called to retrieve users from database and update UserList instance with users
                 *
                 * @param task task to ensure database is properly accessed and data retrieved
                 */
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> diaryList = task.getResult().getDocuments();

                        summaryInformation.updateSubstanceList(diaryList);
                    }
                }
            });
        }
        initializeDates();
    }

    /**
     * Toggles the start and end date buttons.
     */
    private void toggleButtons()
    {
        if (startDateButton.getVisibility() == View.VISIBLE ||
            endDateButton.getVisibility() == View.VISIBLE)
        {
            startDateButton.setVisibility(View.INVISIBLE);
            endDateButton.setVisibility(View.INVISIBLE);

            startDatePicker.setVisibility(View.INVISIBLE);
            endDatePicker.setVisibility(View.INVISIBLE);

            scrollView.setVisibility(View.VISIBLE);
        }

        else
        {
            startDateButton.setVisibility(View.VISIBLE);
            endDateButton.setVisibility(View.VISIBLE);

            startDatePicker.setVisibility(View.INVISIBLE);
            endDatePicker.setVisibility(View.INVISIBLE);

            scrollView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Opens one calendar while closing the other. Calendars are used for
     * choosing start and end of date period.
     *
     * @param d1 The calendar to open
     * @param d2 The calendar to close
     */
    private void openDatePicker(DatePicker d1, DatePicker d2)
    {
        if (d1.getVisibility() == View.VISIBLE)
        {
            d1.setVisibility(View.INVISIBLE);
            d2.setVisibility(View.INVISIBLE);

            scrollView.setVisibility(View.VISIBLE);

            updateSubstanceList();
        }

        else
        {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.INVISIBLE);

            updateSubstanceList();

            scrollView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Changes the start date.
     * @param y Year
     * @param m Month
     * @param d Day of month
     */
    private void changeStartDate(int y, int m, int d)
    {
        startDate = new int[3];

        startDate[0] = y;
        startDate[1] = m + 1;
        startDate[2] = d;

        updateSubstanceList();
    }

    /**
     * Changes the end date.
     * @param y Year
     * @param m Month
     * @param d Day of month
     */
    private void changeEndDate(int y, int m, int d)
    {
        endDate = new int[3];

        endDate[0] = y;
        endDate[1] = m + 1;
        endDate[2] = d;

        updateSubstanceList();
    }

    /**
     * Used to set the start and end date to today by default.
     */
    private void initializeDates()
    {
        changeStartDate(1, 1, 1);
        changeEndDate(1, 1, 1);
    }

    /**
     * Update the substance list after changing date range.
     */
    private void updateSubstanceList()
    {
        scrollViewLayout.removeAllViews();

        Date d, sd, ed;
        TextView tv;

        int len = summaryInformation.getSubstanceList().size();


        for (int i = 0; i < len; i++)
        {
            d = summaryInformation.getSubstanceList().get(i).getDateTime().toDate();
            sd = SubstanceSummaryInformation.parseDate(startDate);
            ed = SubstanceSummaryInformation.parseDate(endDate);


            if(sd.after(ed)){
                ed = sd;
            }
            if (sd != null && ed != null && d != null) {
                if ((d.after(sd) && d.before(ed)) || sameDay(d, sd, ed)) {
                    tv = new TextView(getApplicationContext());
                    tv.setClickable(true);
                    tv.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            String text = ((TextView)v).getText().toString();
                            int number = Character.getNumericValue(text.charAt(0));
                            String id = summaryInformation.getSubstanceList().get(number-1).getId();
                            goToEditEntryPage(id);
                        }
                    });

                    tv.setTextSize(24f);
                    String date = (d.getMonth()+1) + "/"+ d.getDate();
                     tv.setText(i+1 + ". "+ summaryInformation.getSubstanceList().get(i).getName() + "  |  " + date + "  (" +
                            summaryInformation.getSubstanceList().get(i).getDose() + ")");

                    scrollViewLayout.addView(tv);
                }

            }
        }

    }

    /**
     * Changes page to edit intake diary entry.
     * @param id
     */
    private void goToEditEntryPage(String id) {
        Intent intent = new Intent(SubstanceSummaryActivity.this, EditIntakeDiaryEntry.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    /**
     * Compares current date to two dates. Returns true if
     * either date is current.
     * @param current the current date.
     * @param start the selected start date.
     * @param end the selected end date.
     * @return true if current equals start or end, or false otherwise.
     */
    private boolean sameDay(Date current, Date start, Date end){
        if(current.getYear() == start.getYear() && current.getMonth() == start.getMonth()
                && current.getDate() == start.getDate()){
            return true;
        }
        if(current.getYear() == end.getYear() && current.getMonth() == end.getMonth()
                && current.getDate() == end.getDate()){
            return true;
        }
        return false;
    }
    /**
     * needed for logout button fragment
     * @return layoutResourceId
     */
    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_substance_summary;
    }

    /**
     * Called when a menu tab is pressed. Changes the activity to
     * the one matching the tab.
     * @param tab A menu tab. Should be list, summary, or about.
     */
    private void changeTab(TabLayout.Tab tab)
    {
        if (tab.getPosition() == 0)
        {
            Intent intent = new Intent(this, SubstanceListActivity.class);
            startActivity(intent);

            System.out.println("List selected");
        }

        else if (tab.getPosition() == 1)
        {
            Intent intent = new Intent(this, SubstanceSummaryActivity.class);
            startActivity(intent);

            System.out.println("Summary selected");
        }

        else if (tab.getPosition() == 2)
        {
            // TODO: link to about Activity

            System.out.println("About selected");
        }
    }
}
