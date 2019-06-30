package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SubstanceSummaryInstrumentedTest {
    @Rule
    public ActivityTestRule<SubstanceSummaryActivity> dummyRule
       = new ActivityTestRule<>(SubstanceSummaryActivity.class);


    @Test
    public void goToEditPage() {

        onView(withId(R.id.editEntry)).perform(click());
        onView(withId(R.id.EntryEditorTitle)).toString().equals("Edit Entry");

    }
}
