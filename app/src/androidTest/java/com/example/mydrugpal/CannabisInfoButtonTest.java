package com.example.mydrugpal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class CannabisInfoButtonTest {
    @Rule
    public ActivityTestRule<DetailPageActivity> detailRule = new ActivityTestRule<DetailPageActivity>(DetailPageActivity.class);



    @Test
    public void openWhiskeyActivity() {
        onView(withId(R.id.cannabis_button)).perform(click());

        onView(withId(R.id.textViewCannabis))
                .check(matches(withText("Cannabis Info Page")));
    }

}