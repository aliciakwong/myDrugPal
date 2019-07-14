package com.example.mydrugpal;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;

/**
 * Tests the AddToIntakeDiary UI/functionality
 *
 * @author Jocelyn Macdonald, Megan Brock, Ian Sifton
 */

public class AddToIntakeDiaryInstrumentedTest {
    /**
     * Rule for the AddToIntakeDiaryInstrumentedTest activity tests
     *
     */
    @Rule
    public ActivityTestRule<AddToIntakeDiaryActivity> activityRule
            = new ActivityTestRule<>(AddToIntakeDiaryActivity.class);

    /**
     * Tests that the add to intake page opens
     *
     */
    @Test
    public void appOpensAddToIntakePage(){

        Intent intent = new Intent();
        activityRule.launchActivity(intent);
        onView(withId(R.id.addSubstanceView)).toString().equals("Add Substance to Diary");
    }

    /**
     * Tests that the fields can be written to
     * doesn't test the click of the button because the implementation of the current user object
     * involves retrieving data from firestore, which leads to a null document reference
     * can't test the click due to the need for a firestore document reference
     *
     */
    @Test
    public void testTyping() {
        Intent intent = new Intent();
        activityRule.launchActivity(intent);
        onView(withId(R.id.nameOfSubstanceEdit)).perform(typeText("cocaine"), closeSoftKeyboard());
        onView(withId(R.id.typeOfSubstanceEdit)).perform(typeText("stimulant"), closeSoftKeyboard());
        onView(withId(R.id.amountEdit)).perform(typeText("1"), closeSoftKeyboard());
    }

}