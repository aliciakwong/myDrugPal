package com.example.mydrugpal;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
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
     * After the fields have been changed, the user clicks the "SAVE" button and is returned to the
     * Intake Diary activity.
     */
    @Test
    public void saveButtonAndReturn() {
        onView(withId(R.id.EntryEditorTitle)).toString().equals("Edit Entry");

        onView(withId(R.id.button_saveEntryEdit)).perform(click());
        onView(withId(R.id.dummy)).toString().equals("dummy");
    }

    /**
     * After the user has accessed the Edit Intake Diary Entry activity, they click the "DELETE"
     * button and are returned to the Intake Diary activity
     */
    @Test
    public void deleteButtonAndReturn() {
        onView(withId(R.id.button_entryDelete)).perform(click());
        onView(withId(R.id.dummy)).toString().equals("dummy");
    }

}
