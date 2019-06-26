package com.example.mydrugpal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * Activity for the substance summary diary page. Contains buttons for
 * choosing start and end date on calendars. Substances consumed between
 * the two dates are shown in the summary list.
 *
 * @author Megan Brock, Richard Purcell, Alicia Wong
 */
public class SubstanceSummaryActivity extends AppCompatActivity
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

       // TODO: replace string with Substance type when implemented

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
        setContentView(R.layout.activity_substance_summary);

        summaryInformation = new SubstanceSummaryInformation();

        dateSelectButton = findViewById(R.id.selectDateRangeButton);

        startDateButton = findViewById(R.id.startDateButton);
        endDateButton = findViewById(R.id.endDateButton);

        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);

        scrollView = findViewById(R.id.scrollView);
        scrollViewLayout = findViewById(R.id.scrollViewLayout);

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
                changeDate(startDate, year, month, dayOfMonth);
            }
        });

        endDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int dayOfMonth) {
                changeDate(endDate, year, month, dayOfMonth);
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

        //initializeSubstanceList();
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
     * Changes the date of the summary.
     *
     * @param date Size-3 integer array representing date Y/M/D
     * @param y Year
     * @param m Month
     * @param d Date
     */
    private void changeDate(int[] date, int y, int m, int d)
    {
        date[0] = y;
        date[1] = m;
        date[2] = d;

        updateSubstanceList();
    }

    /**
     * Used to set the start and end date to today by default.
     */
    private void initializeDates()
    {
        startDate = new int[3];
        endDate = new int[3];

        changeDate(startDate, 1, 1, 1);
        changeDate(endDate, 1, 1, 1);
    }


    /**
     * Update the substance list after changing date range.
     */
    private void updateSubstanceList()
    {

        scrollViewLayout.removeAllViews();

        //TODO: grab database stuff

        TextView tv = new TextView(getApplicationContext());
        tv.setTextSize(24f);
        tv.setText("Start date: " + startDate[0] + "/" + startDate[1] + "/" + startDate[2]);
        scrollViewLayout.addView(tv);

        tv = new TextView(getApplicationContext());
        tv.setTextSize(24f);
        tv.setText("End date: " + endDate[0] + "/" + endDate[1] + "/" + endDate[2]);
        scrollViewLayout.addView(tv);

        // TODO: remove start/end date debug entries

        int len = summaryInformation.getSubstanceList().size();

        for (int i = 0; i < len; i++)
        {
            tv = new TextView(getApplicationContext());
            tv.setTextSize(24f);
            tv.setText(summaryInformation.getSubstanceList().get(i).getName());

            scrollViewLayout.addView(tv);
        }
    }
}
