package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * JUnit Tests for logout page business logic
 * @author Emma Travers, Alicia Wong
 */
public class LogoutInstrumentedTest {

    @Rule
    public ActivityTestRule<SubstanceListActivity> activityRule
            = new ActivityTestRule<>(SubstanceListActivity.class);

    /**
     * test to check that once a user clicks the Logout button, they are returned to the Login page
     */
    @Test
    public void logoutTransition() {
        onView(withId(R.id.button_logout)).perform(click());
        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

    }





}