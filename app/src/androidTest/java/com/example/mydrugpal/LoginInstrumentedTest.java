package com.example.mydrugpal;

import android.app.Instrumentation;
import android.widget.Button;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;



public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule
            = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void appOpensToLogin(){
        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

    }

    @Test
    public void loginTransition() {

        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

        onView(withId(R.id.enterEmail)).perform(typeText("user@email.com"));
        onView(withId(R.id.enterPassword)).perform(typeText("password"));
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.LoginSuccessView)).toString().equals("You Have Successfully Logged");

    }

    @Test
    public void loginFailed() {

        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

        onView(withId(R.id.enterEmail)).perform(typeText("user"));
        onView(withId(R.id.enterPassword)).perform(typeText("pw"));
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.enterEmail)).toString().equals("");
        onView(withId(R.id.enterPassword)).toString().equals("");

    }


}
