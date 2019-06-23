package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * Tests UI for substance summary page
 *
 * @author Megan Brock, Richard Purcell
 */
public class SubstanceSummaryInstrumentedTests
{
    public Button dateSelectButton;

    public Button startDateButton;
    public Button endDateButton;

    public DatePicker startDatePicker;
    public DatePicker endDatePicker;

    @Rule
    public ActivityTestRule<SubstanceSummaryActivity> activityRule
            = new ActivityTestRule<>(SubstanceSummaryActivity.class);

    /**
     * Tests standard use case. Click select date button to open
     * start/end date buttons, click start date button, click end
     * date button, click select date button to close.
     */
    @Test
    public void TestStandardUseCase() {
        dateSelectButton = activityRule.getActivity().dateSelectButton;
        startDateButton = activityRule.getActivity().startDateButton;
        endDateButton = activityRule.getActivity().endDateButton;

        startDatePicker = activityRule.getActivity().startDatePicker;
        endDatePicker = activityRule.getActivity().endDatePicker;

        // Test 1 - click select date
        // result - start and end select buttons show up

        Assert.assertTrue(startDateButton.getVisibility() == View.INVISIBLE && endDateButton.getVisibility() == View.INVISIBLE);
        Assert.assertTrue(startDatePicker.getVisibility() == View.INVISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);

        onView(withId(R.id.selectDateRangeButton)).perform(click());

        Assert.assertTrue(startDateButton.getVisibility() == View.VISIBLE && endDateButton.getVisibility() == View.VISIBLE);
        Assert.assertTrue(startDatePicker.getVisibility() == View.INVISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);

        // Test 2 - click select start date
        // result - start date picker opens

        onView(withId(R.id.startDateButton)).perform(click());

        Assert.assertTrue(startDatePicker.getVisibility() == View.VISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);

        // Test 3 - click select end date
        // result - start date picker closes, end date picker opens

        onView(withId(R.id.endDateButton)).perform(click());

        Assert.assertTrue(endDatePicker.getVisibility() == View.VISIBLE && startDatePicker.getVisibility() == View.INVISIBLE);

        // Test 4 - click select date
        // result - start and end buttons and pickers disappear

        onView(withId(R.id.selectDateRangeButton)).perform(click());

        Assert.assertTrue(startDateButton.getVisibility() == View.INVISIBLE && endDateButton.getVisibility() == View.INVISIBLE);
        Assert.assertTrue(startDatePicker.getVisibility() == View.INVISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);
    }
}
