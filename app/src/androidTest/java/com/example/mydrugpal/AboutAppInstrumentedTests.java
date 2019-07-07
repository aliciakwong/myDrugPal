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
        //TODO: add the logic
    }

    /**
     * Tests that that a go to company web page is clickable.
     */
    @Test
    public void goToCompanyWebsite(){

    }

    /**
     * Tests that that an email info button is active in some way.
     */
    @Test
    public void clickEmail(){
        //TODO: add the logic
    }


}
