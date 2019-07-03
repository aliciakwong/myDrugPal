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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;

/**
 * Activity for the substance summary diary page. Contains buttons for
 * choosing start and end date on calendars. Substances consumed between
 * the two dates are shown in the summary list.
 *
 * @author Megan Brock, Richard Purcell, Alicia Wong
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

    public ScrollView scrollView;
    public LinearLayout scrollViewLayout;

    public Button viewSubstanceButton;

    private SubstanceSummaryInformation summaryInformation;

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

        startDate = new int[3];
        endDate = new int[3];

        dateSelectButton = findViewById(R.id.selectDateRangeButton);

        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);

        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);

        scrollView = findViewById(R.id.scrollView);
        scrollViewLayout = findViewById(R.id.scrollViewLayout);

        viewSubstanceButton = findViewById(R.id.viewSubstanceButton);

        dateSelectButton.setOnClickListener(new View.OnClickListener()
        {
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


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userDocument = database.collection("Users").
                                                document(CurrentUser.getInstance().GetEmail());
        CollectionReference userIntakeDiary = userDocument.collection("IntakeDiary");
        userIntakeDiary.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            /**
             * method called to retrieve users from database and update UserList instance with users
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

        initializeDates();

        viewSubstanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetailPage();
            }
        });

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
            d = SubstanceSummaryInformation.parseDate(summaryInformation.getSubstanceList().get(i).getDateTime());
            sd = SubstanceSummaryInformation.parseDate(startDate);
            ed = SubstanceSummaryInformation.parseDate(endDate);

            if (sd != null && ed != null && d != null) {
                if ((d.after(sd) && d.before(ed)) || d.equals(sd) || d.equals(ed)) {
                    tv = new TextView(getApplicationContext());
                    tv.setClickable(true);
                    tv.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            // TODO add edit transition
                            //TODO: USE THIS to get the substance name and date ((TextView)v).getText().toString());
                            //TODO: to get id: summaryInformation.getSubstanceList().get(i).getId();

                        }
                    });

                    tv.setTextSize(24f);
                    String date = (d.getMonth()+1) + "/"+ d.getDate();
                    tv.setText(summaryInformation.getSubstanceList().get(i).getName() + " " + date);

                    scrollViewLayout.addView(tv);
                }

            }
            //TODO: order scrollview layout by date
        }

    }
    private void goToDetailPage(){
        Intent intent = new Intent(this, DetailPageActivity.class);
        startActivity(intent);
    }
    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_substance_summary;
    }

}
