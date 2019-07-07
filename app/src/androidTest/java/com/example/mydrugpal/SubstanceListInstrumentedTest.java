package com.example.mydrugpal;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;

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
    ViewInteraction substanceSummaryButton = null;

    /**
     * Finds addSubstanceButton, substanceSummaryButton references
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        substanceName = onView(withId(R.id.nameOfSubstanceEdit));
        addSubstanceButton = onView(withId(R.id.addSubstance));
        substanceSummaryButton = onView(withId(R.id.substanceSummaryButton));
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
     * Tests that add substance button is displayed and clickable
     */
    @Test
    public void testAddSubstanceButton() {
        addSubstanceButton.perform(click());
    }

    /**
     * Tests that the substance summary button is displayed and clickable
     */
    @Test
    public void testSubstanceSummaryButton() { substanceSummaryButton.perform((click())); }
}
