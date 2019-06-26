package com.example.mydrugpal;

import androidx.test.espresso.ViewInteraction;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Espresso tests which for the AddToIntakeDiaryActivity
 *
 * @author Ian Sifton, Jocelyn MacDonald
 */
public class AddToIntakeDiaryInstrumentedTest {
    @Rule
    public ActivityTestRule<DetailPageActivity> detailRule = new ActivityTestRule<DetailPageActivity>(DetailPageActivity.class);

    ViewInteraction substanceName = null;
    ViewInteraction addSubstanceButton = null;

    @Before
    public void setUp() throws Exception {
        substanceName = onView(withId(R.id.nameOfSubstanceEdit));
        addSubstanceButton = onView(withId(R.id.addSubstance));
    }
/*
    @Test
    public void substanceListButton() {
        substanceListButton
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }
*/

    @Test
    public void addSubstanceButton() {
        addSubstanceButton.perform(click());
    }
}


