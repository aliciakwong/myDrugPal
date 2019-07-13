package com.example.mydrugpal;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Espresso tests which for the AddToIntakeDiaryActivity
 *
 * @author Ian Sifton, Jocelyn MacDonald, Richard Purcell, Emma Travers, Megan Brock, Alicia Wong
 */
public class SubstanceListInstrumentedTest {
    public TabLayout layout;
    public TabLayout.Tab list;
    public TabLayout.Tab diary;
    public TabLayout.Tab about;

    /**
     * Rule for tests to display detail page
     */
    @Rule
    public ActivityTestRule<SubstanceListActivity> detailRule = new ActivityTestRule<SubstanceListActivity>(SubstanceListActivity.class);

    ViewInteraction substanceName = null;
    ViewInteraction addSubstanceButton = null;

    /**
     * Finds addSubstanceButton, substanceSummaryButton references
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        substanceName = onView(withId(R.id.nameOfSubstanceEdit));
        addSubstanceButton = onView(withId(R.id.addSubstance));
    }

    /**
     * Test that the menu bar is visible, all three tabs
     * are visible and have the right text.
     */
    @Test
    public void TestMenuBarVisible() {
        layout = detailRule.getActivity().layout;
        list = detailRule.getActivity().list;
        diary = detailRule.getActivity().diary;
        about = detailRule.getActivity().about;

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
        layout = detailRule.getActivity().layout;
        list = detailRule.getActivity().list;
        diary = detailRule.getActivity().diary;
        about = detailRule.getActivity().about;

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
        layout = detailRule.getActivity().layout;
        list = detailRule.getActivity().list;
        diary = detailRule.getActivity().diary;
        about = detailRule.getActivity().about;

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
        layout = detailRule.getActivity().layout;
        list = detailRule.getActivity().list;
        diary = detailRule.getActivity().diary;
        about = detailRule.getActivity().about;

        onView(ViewMatchers.withText("About"))
                .perform(ViewActions.click());

        onView(withId(R.id.AboutAppTitle));
    }

    /**
     * Tests that add substance button is displayed and clickable
     */
    @Test
    public void testAddSubstanceButton() {
        addSubstanceButton.perform(click());
    }
}
