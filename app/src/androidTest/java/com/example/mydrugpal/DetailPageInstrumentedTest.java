package com.example.mydrugpal;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

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
public class DetailPageInstrumentedTest {
    /**
     * Rule for tests to display detail page
     */
    @Rule
    public ActivityTestRule<SubstanceListActivity> detailRule = new ActivityTestRule<SubstanceListActivity>(SubstanceListActivity.class);

    ViewInteraction substanceName = null;
    ViewInteraction addSubstanceButton = null;

    /**
     * ensures substancelist and addsubstance buttons are displayed
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        substanceName = onView(withId(R.id.nameOfSubstanceEdit));
        addSubstanceButton = onView(withId(R.id.addSubstance));
    }

    /**
     * test that add substance button is displayed and clickable
     */
    @Test
    public void addSubstanceButton() {
        addSubstanceButton.perform(click());
    }


}
