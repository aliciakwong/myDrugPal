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
 * Tests the AddToIntakeDiary UI/functionality
 *
 * @author Jocelyn Macdonald, Megan Brock, Emma Travers
 */
public class AddToIntakeDiaryInstrumentedTest {
    /**
     * Rule for the registration activity tests
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
        onView(withId(R.id.addSubstanceView)).toString().equals("Add Substance to Diary");

    }

    /**
     * Tests that the fields can be written to
     *
     */
    @Test
    public void testTyping() {
        onView(withId(R.id.nameOfSubstanceEdit)).perform(typeText("cocaine"), closeSoftKeyboard());
        onView(withId(R.id.typeOfSubstanceEdit)).perform(typeText("stimulant"), closeSoftKeyboard());
        onView(withId(R.id.amountEdit)).perform(typeText("1"), closeSoftKeyboard());

    }

    /**
     * Tests that the button can be clicked
     *
     */
    @Test
    public void testAddButton() {
        onView(withId(R.id.addSubstancebutton)).perform(click());
    }

}