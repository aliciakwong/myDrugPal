package com.example.mydrugpal;

mport androidx.test.filters.LargeTest;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@LargeTest

public class ExpressoTest {
    @Rule
    public ActivityTestRule<DetailPageActivity> menuActivityTestRule =
            new ActivityTestRule<>(DetailPageActivity.class, true, true);

    /**
     * Test password entered is valid with button click and successful validation;
     */
    @Test
    public void buttonChangesText()
    {
        onView(withId(R.id.heisengberg_button)).perform(click());
        intended(hasComponent(HeisenbergInfoActivity.class.getName()));
    }


}
