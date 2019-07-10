package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LogIntakeNotificationInstrumentedTests {

    @Rule
    public ActivityTestRule<LogIntakeNotificationActivity> activityRule
            = new ActivityTestRule<>(LogIntakeNotificationActivity.class);

    /**
     * test to check that dummy entry time is within the past 24 hour period
     */
    @Test
    public void timeEntryWithinTimeWindow(){
        //TODO: add test logic

    }

    /**
     * test to check that dummy entry time is not within the past 24 hour period
     * (it has been more than a day since an entry has been made)
     */
    @Test
    public void timeEntryOutsideTimeWindow(){
        //TODO: add test logic

    }
}
