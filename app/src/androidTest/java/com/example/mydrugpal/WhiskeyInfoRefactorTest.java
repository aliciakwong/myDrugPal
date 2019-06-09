package com.example.mydrugpal;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiskeyInfoRefactorTest {
    @Rule
    public ActivityTestRule<WhiskeyInfoRefactor> whiskeyTestRule = new ActivityTestRule<WhiskeyInfoRefactor>(WhiskeyInfoRefactor.class);

    public WhiskeyInfoRefactor whiskeyActivity = null;

    @Before
    public void setUp() throws Exception {
        whiskeyActivity = whiskeyTestRule.getActivity();
    }

    @Test
    public void pageLaunch() {
        View activityView = whiskeyActivity.findViewById(R.id.textViewWhiskey);
        assertNotNull(activityView);
    }

    @After
    public void tearDown() throws Exception {
        whiskeyActivity = null;
    }
}