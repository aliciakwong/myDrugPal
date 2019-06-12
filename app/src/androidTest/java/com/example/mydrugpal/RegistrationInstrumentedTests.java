package com.example.mydrugpal;

import android.preference.EditTextPreference;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;

public class RegistrationInstrumentedTests {
    @Rule
    public ActivityTestRule<RegistrationActivity> activityRule
            = new ActivityTestRule<>(RegistrationActivity.class);


    @Test
    public void appOpensRegistration(){
        onView(withId(R.id.textView_fName)).toString().equals("First name");
        onView(withId(R.id.textView_lName)).toString().equals("Last name");
        onView(withId(R.id.textView_Email)).toString().equals("Email");
        onView(withId(R.id.textView_Password)).toString().equals("Password");

    }
    @Test
    public void testRegister() {
        onView(withId(R.id.editText_fName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editText_lName)).perform(typeText("lastname"), closeSoftKeyboard());
        onView(withId(R.id.editText_Email)).perform(typeText("email"), closeSoftKeyboard());
        onView(withId(R.id.editText_Password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.register_button)).perform(click());
    }
    @Test
    public void testClearButton() {
        onView(withId(R.id.editText_fName)).perform(typeText("name"), closeSoftKeyboard());
        onView(withId(R.id.editText_lName)).perform(typeText("lastname"), closeSoftKeyboard());
        onView(withId(R.id.editText_Email)).perform(typeText("email"), closeSoftKeyboard());
        onView(withId(R.id.editText_Password)).perform(typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.clear_button)).perform(click());
        onView(withId(R.id.textView_fName)).toString().equals("");
        onView(withId(R.id.textView_lName)).toString().equals("");
        onView(withId(R.id.textView_Email)).toString().equals("");
        onView(withId(R.id.textView_Password)).toString().equals("");
    }


}
