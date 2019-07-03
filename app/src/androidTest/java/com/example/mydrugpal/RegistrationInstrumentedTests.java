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
 * Tests the registration UI/functionality
 *
 * @author Jocelyn Macdonald, Megan Brock, Emma Travers
 */
public class RegistrationInstrumentedTests {
    /**
     * Rule for the registration activity tests
     *
     */
    @Rule
    public ActivityTestRule<RegistrationActivity> activityRule
            = new ActivityTestRule<>(RegistrationActivity.class);

    /**
     * Tests that the registration action opens in the app with correct strings
     *
     */
    @Test
    public void appOpensRegistration(){
        onView(withId(R.id.textView_fName)).toString().equals("First name");
        onView(withId(R.id.textView_lName)).toString().equals("Last name");
        onView(withId(R.id.textView_Email)).toString().equals("Email");
        onView(withId(R.id.textView_Password)).toString().equals("Password");
    }

    /**
     * Tests that the fields can be written to and the register button can be pressed
     *
     */
    @Test
    public void testTyping() {
        onView(withId(R.id.editText_fName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editText_lName)).perform(typeText("lastname"), closeSoftKeyboard());
        onView(withId(R.id.editText_Email)).perform(typeText("Email"), closeSoftKeyboard());
        onView(withId(R.id.editText_Password)).perform(typeText("password"), closeSoftKeyboard());

    }

    /**
     * Tests that the fields are cleared when the clear button is pressed
     *
     */
    @Test
    public void testClearButton() {
        onView(withId(R.id.editText_fName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editText_lName)).perform(typeText("lastname"), closeSoftKeyboard());
        onView(withId(R.id.editText_Email)).perform(typeText("Email"), closeSoftKeyboard());
        onView(withId(R.id.editText_Password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.clear_button)).perform(click());
        onView(withId(R.id.textView_fName)).toString().equals("");
        onView(withId(R.id.textView_lName)).toString().equals("");
        onView(withId(R.id.textView_Email)).toString().equals("");
        onView(withId(R.id.textView_Password)).toString().equals("");
    }

    /**
     * Tests that a success message is shown when account registration is successful
     *
     */
    @Test
    public void testSuccessMessage() {
        onView(withId(R.id.editText_fName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editText_lName)).perform(typeText("lastname"), closeSoftKeyboard());
        onView(withId(R.id.editText_Email)).perform(typeText("Email"), closeSoftKeyboard());
        onView(withId(R.id.editText_Password)).perform(typeText("password"), closeSoftKeyboard());

        onView(withId(R.id.clear_button)).perform(click());
        onView(withId(R.id.textView_Message)).toString().equals("Profile successfully created!");

    }

    /**
     * Tests that the error message is shown when account registration fails
     *
     */
    @Test
    public void testFailMessage() {
        onView(withId(R.id.editText_fName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editText_lName)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editText_Email)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.editText_Password)).perform(typeText(""), closeSoftKeyboard());

        onView(withId(R.id.clear_button)).perform(click());
        onView(withId(R.id.textView_Message)).toString().equals("Profile could not be created!");
    }
}
