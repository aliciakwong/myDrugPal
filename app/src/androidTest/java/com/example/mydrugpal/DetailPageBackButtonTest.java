package com.example.mydrugpal;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
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

public class DetailPageBackButtonTest {

    @Rule
    public ActivityTestRule<DetailPageActivity> detailRule = new ActivityTestRule<DetailPageActivity>(DetailPageActivity.class);
    ViewInteraction whiskeyButton = null;
    ViewInteraction acidButton = null;
    ViewInteraction cannabisButton = null;
    ViewInteraction cocacolaButton = null;
    ViewInteraction heisenbergButton = null;

    @Before
    public void setUp() throws Exception {
        whiskeyButton = onView(withId(R.id.whiskey_button));
        acidButton = onView(withId(R.id.acid_button));
        cannabisButton = onView(withId(R.id.cannabis_button));
        cocacolaButton = onView(withId(R.id.cocacola_button));
        heisenbergButton = onView(withId(R.id.heisengberg_button));
    }

    @Test
    public void tearDown() throws Exception {
        // click whiskey. Wait 3 seconds. The return to DetailPageActivity
        whiskeyButton.perform(click());

        // confirm arrive on whiskey info page
        onView(withId(R.id.textViewWhiskey))
                .check(matches(withText("Whiskey Info Page")));

        // wait 3 seconds
//        onView(withId(R.id.textViewWhiskey)).wait(4000);

        // press back button
        onView(isRoot()).perform(ViewActions.pressBack());

        // confirm that view is DetailPage.
        onView(withId(R.id.whiskey_button));
    }
}