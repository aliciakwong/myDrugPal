package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Espresso Tests for editing a user's intake diary (editing and deleting)
 * @author Ian Sifton, Emma Travers
 */
public class EditIntakeDiaryEntryInstrumentedTest {

    /**
     * Specifies and launches a page to run for the Espresso tests
     */
    @Rule
    public ActivityTestRule<EditIntakeDiaryEntry> activityRule
            = new ActivityTestRule<>(EditIntakeDiaryEntry.class);


    /**
     * test to make sure that the user is on the correct page with the title: "Edit Entry"
     */
    @Test
    public void appOpensToEntryEditor(){
        onView(withId(R.id.EntryEditorTitle)).toString().equals("Edit Entry");
    }

    /**
     * Make sure that when the user edits on the page that all entries are filled.
     */
    @Test
    public void editFormFields() {
        onView(withId(R.id.EntryEditorTitle)).toString().equals("Edit Entry");

        onView(withId(R.id.editTextSubstanceName)).perform(clearText(), typeText("Mushrooms"), closeSoftKeyboard());
        onView(withId(R.id.editTextSubstanceType)).perform(clearText(), typeText("Psychedelics"), closeSoftKeyboard());
        onView(withId(R.id.editTextSubstanceAmount)).perform(clearText(), typeText("15"), closeSoftKeyboard());
    }



    /**
     * Checks if the save entry button is visible on the page.
     */
    @Test
    public void saveButtonVisible() {
        onView(withId(R.id.EntryEditorTitle)).toString().equals("Edit Entry");

//        onView(withId(R.id.button_saveEntryEdit)).perform(click());
        onView(withId(R.id.button_saveEntryEdit)).check(matches(isDisplayed()));

    }

    /**
     * Checks if the delete entry button is visible on the page.
     */
    @Test
    public void deleteButtonVisible() {
//        onView(withId(R.id.button_entryDelete)).perform(click());
        onView(withId(R.id.button_entryDelete)).check(matches(isDisplayed()));
    }

}
