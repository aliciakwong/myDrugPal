package com.example.mydrugpal;

import com.example.mydrugpal.model.NewIntakeEntry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * unit test for the IntakeDiaryEntry class
 * @author Jocelyn MacDonald, Ian Sifton
 */

public class IntakeDiaryEntryUnitTest {

    /**
     * tests that the entry is not null
     */
    @Test
    public void intakeEntry_NotEmpty()
    {
        NewIntakeEntry entry = new NewIntakeEntry("fentanyl", "narcotic", "3");

        assertNotNull(entry.getNameOfSubstance());
        assertNotNull(entry.getTypeOfSubstance());
        assertNotNull(entry.getDose());
        assertNotNull(entry.getTime());
    }

    /**
     * tests that the type of the substance was set correctly
     */
    @Test
    public void getNameOfSubstance() {
        NewIntakeEntry entry = new NewIntakeEntry("fentanyl", "narcotic", "3");

        assertEquals(entry.getNameOfSubstance(), "fentanyl");
    }


    /**
     * tests that the amount of the substance was set correctly
     */
    @Test
    public void getAmount() {
        NewIntakeEntry entry = new NewIntakeEntry("fentanyl", "narcotic", "3");

        assertEquals(entry.getDose(), "3");
    }

    /**
     * tests the timestamp was set and is not null
     */
    @Test
    public void getTypeOfSubstance() {
        NewIntakeEntry entry = new NewIntakeEntry("fentanyl", "narcotic", "3");

        assertEquals(entry.getTypeOfSubstance(), "narcotic");
    }
}