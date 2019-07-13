package com.example.mydrugpal;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests UI for substance summary page
 *
 * @author Megan Brock, Richard Purcell, Alicia Wong, Ian Sifton
 */
public class SubstanceSummaryInstrumentedTests
{
    public Button dateSelectButton;

    public Button startDateButton;
    public Button endDateButton;

    public DatePicker startDatePicker;
    public DatePicker endDatePicker;

    public TabLayout layout;
    public TabLayout.Tab list;
    public TabLayout.Tab diary;
    public TabLayout.Tab about;

    @Rule
    public ActivityTestRule<SubstanceSummaryActivity> activityRule
            = new ActivityTestRule<>(SubstanceSummaryActivity.class);


    /**
     * Test that the menu bar is visible, all three tabs
     * are visible and have the right text.
     */
    @Test
    public void TestMenuBarVisible() {
        layout = activityRule.getActivity().layout;
        list = activityRule.getActivity().list;
        diary = activityRule.getActivity().diary;
        about = activityRule.getActivity().about;

        Assert.assertNotNull(layout);
        Assert.assertNotNull(list);
        Assert.assertNotNull(diary);
        Assert.assertNotNull(about);

        Assert.assertEquals(list.getText().toString(), "List");
        Assert.assertEquals(diary.getText().toString(), "Summary");
        Assert.assertEquals(about.getText().toString(), "About");
    }

    /**
     * Tests that pressing the list tab will open the substance
     * list activity.
     */
    @Test
    public void TestListTab()
    {
        layout = activityRule.getActivity().layout;
        list = activityRule.getActivity().list;
        diary = activityRule.getActivity().diary;
        about = activityRule.getActivity().about;

        onView(ViewMatchers.withText("List"))
                .perform(ViewActions.click());

        onView(withId(R.id.addSubstancebutton));
    }

    /**
     * Tests that pressing the diary tab will open the substance
     * summary activity.
     */
    @Test
    public void TestDiaryTab()
    {
        layout = activityRule.getActivity().layout;
        list = activityRule.getActivity().list;
        diary = activityRule.getActivity().diary;
        about = activityRule.getActivity().about;

        onView(ViewMatchers.withText("Summary"))
                .perform(ViewActions.click());

        onView(withId(R.id.selectDateRangeButton));
    }

    /**
     * Tests that pressing the about tab will open the substance
     * about activity.
     */
    @Test
    public void TestAboutTab()
    {
        layout = activityRule.getActivity().layout;
        list = activityRule.getActivity().list;
        diary = activityRule.getActivity().diary;
        about = activityRule.getActivity().about;

        onView(ViewMatchers.withText("About"))
                .perform(ViewActions.click());

        onView(withId(R.id.AboutAppTitle));
    }

    /**
     * Tests that the select date button will enable the start
     * and end date buttons and the start date picker when
     * pressed for the first time.
     */
    @Test
    public void TestSelectDateRangeOpen() {
        dateSelectButton = activityRule.getActivity().dateSelectButton;
        startDateButton = activityRule.getActivity().startDateButton;
        endDateButton = activityRule.getActivity().endDateButton;

        startDatePicker = activityRule.getActivity().startDatePicker;
        endDatePicker = activityRule.getActivity().endDatePicker;

        Assert.assertTrue(startDateButton.getVisibility() == View.INVISIBLE && endDateButton.getVisibility() == View.INVISIBLE);
        Assert.assertTrue(startDatePicker.getVisibility() == View.INVISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);

        onView(withId(R.id.selectDateRangeButton)).perform(click());

        Assert.assertTrue(startDateButton.getVisibility() == View.VISIBLE && endDateButton.getVisibility() == View.VISIBLE);
        Assert.assertTrue(startDatePicker.getVisibility() == View.INVISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);
    }

    /**
     * Tests that the start date picker opens when the start
     * date button is pressed.
     */
    @Test
    public void TestStartDatePicker() {
        dateSelectButton = activityRule.getActivity().dateSelectButton;
        startDateButton = activityRule.getActivity().startDateButton;
        endDateButton = activityRule.getActivity().endDateButton;

        startDatePicker = activityRule.getActivity().startDatePicker;
        endDatePicker = activityRule.getActivity().endDatePicker;

        onView(withId(R.id.selectDateRangeButton)).perform(click());
        onView(withId(R.id.startDateButton)).perform(click());

        Assert.assertTrue(startDatePicker.getVisibility() == View.VISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);

    }

    /**
     * Tests that the end date picker opens when the end
     * date button is pressed.
     */
    @Test
    public void TestEndDatePicker() {
        dateSelectButton = activityRule.getActivity().dateSelectButton;
        startDateButton = activityRule.getActivity().startDateButton;
        endDateButton = activityRule.getActivity().endDateButton;

        startDatePicker = activityRule.getActivity().startDatePicker;
        endDatePicker = activityRule.getActivity().endDatePicker;

        onView(withId(R.id.selectDateRangeButton)).perform(click());
        onView(withId(R.id.endDateButton)).perform(click());

        Assert.assertTrue(endDatePicker.getVisibility() == View.VISIBLE && startDatePicker.getVisibility() == View.INVISIBLE);
    }

    /**
     * Tests that the start and end buttons and
     * date pickers close when the select date range button
     * is pressed the second time.
     */
    @Test
    public void TestSelectDateRangeClose() {
        dateSelectButton = activityRule.getActivity().dateSelectButton;
        startDateButton = activityRule.getActivity().startDateButton;
        endDateButton = activityRule.getActivity().endDateButton;

        startDatePicker = activityRule.getActivity().startDatePicker;
        endDatePicker = activityRule.getActivity().endDatePicker;

        onView(withId(R.id.selectDateRangeButton)).perform(click());
        onView(withId(R.id.selectDateRangeButton)).perform(click());

        Assert.assertTrue(startDateButton.getVisibility() == View.INVISIBLE && endDateButton.getVisibility() == View.INVISIBLE);
        Assert.assertTrue(startDatePicker.getVisibility() == View.INVISIBLE && endDatePicker.getVisibility() == View.INVISIBLE);
    }
}
