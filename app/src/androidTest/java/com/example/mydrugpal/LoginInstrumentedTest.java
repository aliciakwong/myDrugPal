package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

/**
 * JUnit Tests for login page business logic
 * @author Jocelyn MacDonald, Alicia Wong, Ian Sifton
 */
public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule
            = new ActivityTestRule<>(LoginActivity.class);

    /**
     * test to check that first activity after launch is the login screen
     */
    @Test
    public void appOpensToLogin(){
        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

    }

    /**
     * test to check that once a valid user is entered, the activity switches to a profile page
     */
    @Test
    public void loginTransition() {

        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

        onView(withId(R.id.enterEmail)).perform(typeText("user@Email.com"));
        onView(withId(R.id.enterPassword)).perform(typeText("password"));
        onView(withId(R.id.button_login)).perform(click());

        onView(withId(R.id.LoginSuccessView)).toString().equals("You Have Successfully Logged In");

    }

    /**
     * test to check that when an invalid user is entered, the login page clears and is still displayed
     */
    @Test
    public void loginFailed() {

        onView(withId(R.id.Title_MyDrugPal)).toString().equals("MyDrugPal");

        onView(withId(R.id.enterEmail)).perform(typeText("user"));
        onView(withId(R.id.enterPassword)).perform(typeText("pw"));
        onView(withId(R.id.button_login)).perform(click());

        onView(withId(R.id.enterEmail)).toString().equals("");
        onView(withId(R.id.enterPassword)).toString().equals("");

    }


}