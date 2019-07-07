package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;

/**
 * Tests the AboutApp UI/functionality
 *
 * @author Alicia Wong, Richard Purcell
 */

public class AboutAppInstrumentedTests {
    /**
     * Rule for the AboutApp activity tests (needs activity page created)
     *
     */
    @Rule
    public ActivityTestRule<AboutAppActivity> activityRule
            = new ActivityTestRule<>(AboutAppActivity.class);

    /**
     * Tests that the go to AboutApp button opens in aboutApp information page
     * and displays correct information.
     */
    @Test
    public void appOpensAboutApp(){
        onView(withId(R.id.AboutAppButton)).toString().equals("About myDrugPal");
    }

    /**
     * Tests that that a go to the gitlab repository  is clickable.
     */
    @Test
    public void goToGitLabRepository(){
        onView(withId(R.id.gitLabLink)).toString().equals("https://git.cs.dal.ca/alicia/mydrugpal");

    }

    /**
     * Tests that the dev team info box is populated
     */
    @Test
    public void devTeamInfoVisible(){
        onView(withId(R.id.devTeamInfoBox)).toString().equals("Information about the development team goes here..");
    }

    /**
     * Tests that the about app info box is populated
     */
    @Test
    public void appInfoVisible(){
        onView(withId(R.id.appInfoBox)).toString().equals("Information about the app goes here.");
    }





}
