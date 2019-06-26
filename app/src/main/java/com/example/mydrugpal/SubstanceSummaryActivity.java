package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Activity for the substance summary diary page. Contains buttons for
 * choosing start and end date on calendars. Substances consumed between
 * the two dates are shown in the summary list.
 *
 * @author Megan Brock, Richard Purcell
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

    private List<String> substanceList;   // TODO: replace string with Substance type when implemented

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

        initializeDates();
        initializeSubstanceList();
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
        }

        else
        {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.INVISIBLE);

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

        Date d = Calendar.getInstance().getTime();

        changeDate(startDate, d.getYear() + 1900, d.getMonth(), d.getDate());
        changeDate(endDate, d.getYear() + 1900, d.getMonth(), d.getDate());
    }

    /**
     * Set the substance list for current date.
     */
    private void initializeSubstanceList()
    {
        // TODO: replace empty strings with substances
        substanceList = new ArrayList<String>();

        for (int i = 0; i < 100; i++)
        {
            substanceList.add("Drug Name\tDrug Type\tDrug Amount");
        }
        // TODO: replace empty strings with substances

        // TODO: remove start/end date debug entries
        TextView tv = new TextView(getApplicationContext());
        tv.setTextSize(24f);
        tv.setText("Start date: " + startDate[0] + "/" + startDate[1] + "/" + startDate[2]);
        scrollViewLayout.addView(tv);

        tv = new TextView(getApplicationContext());
        tv.setTextSize(24f);
        tv.setText("End date: " + endDate[0] + "/" + endDate[1] + "/" + endDate[2]);
        scrollViewLayout.addView(tv);
        // TODO: remove start/end date debug entries

        updateSubstanceList();
    }

    /**
     * Update the substance list after changing date range.
     */
    private void updateSubstanceList()
    {
        // TODO: remove start/end date debug entries
        TextView tv = new TextView(getApplicationContext());
        tv.setTextSize(20f);
        tv.setText("Start date: " + startDate[0] + "/" + startDate[1] + "/" + startDate[2]);
        scrollViewLayout.addView(tv);

        tv = new TextView(getApplicationContext());
        tv.setTextSize(20f);
        tv.setText("End date: " + endDate[0] + "/" + endDate[1] + "/" + endDate[2]);
        scrollViewLayout.addView(tv);
        // TODO: remove start/end date debug entries

        // TODO: update substance list from FireStore DB between start and end dates

        int len = substanceList.size();

        for (int i = 0; i < len; i++)
        {
            tv = new TextView(getApplicationContext());
            tv.setTextSize(20f);
            tv.setText(substanceList.get(i));

            scrollViewLayout.addView(tv);
        }
    }
}
