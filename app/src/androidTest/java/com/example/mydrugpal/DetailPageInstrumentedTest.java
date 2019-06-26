package com.example.mydrugpal;

import android.view.View;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Espresso tests which for the AddToIntakeDiaryActivity
 *
 * @author Ian Sifton, Jocelyn MacDonald
 */
public class DetailPageInstrumentedTest {
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
