package com.example.mydrugpal;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Espresso testing AddToIntakeDiaryActivity.java, validating that the proper fields are present and the appropriate
 * EditText fields are editable
 *
 * @author Ian Sifton, Jocelyn MacDonald
 */
public class AddToIntakeDiaryActivityTest {
    @Rule
    public ActivityTestRule<SubstanceDetail> detailRule = new ActivityTestRule<SubstanceDetail>(SubstanceDetail.class);

    ViewInteraction substanceName = null;
    ViewInteraction substanceDesc = null;


    @Before
    public void setUp() throws Exception {
        substanceName = onView(withId(R.id.substanceName));
        substanceDesc = onView(withId(R.id.substanceMainInfo));
    }


}