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

public class HeisenbergInfoButtonTest {
    @Rule
    public ActivityTestRule<DetailPageActivity> detailRule = new ActivityTestRule<DetailPageActivity>(DetailPageActivity.class);
//    public ActivityTestRule<WhiskeyInfoRefactor> whiskeyRule
//            = new ActivityTestRule<>(WhiskeyInfoRefactor.class);

//    @Before
//    public void setUp() throws Exception {
//        onView
//    }


    @Test
    public void openWhiskeyActivity() {
        onView(withId(R.id.heisengberg_button)).perform(click());

        onView(withId(R.id.textViewHeisenberg))
                .check(matches(withText("Heisenberg Info Page")));
    }


//    @After
//    public void tearDown() throws Exception {
//    }
}