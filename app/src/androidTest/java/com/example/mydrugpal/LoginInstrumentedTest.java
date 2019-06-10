package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule
            = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void appOpensToLogin(){
        onView(withId(R.id.appTitle)).toString().equals("MyDrugPal");

    }


}
