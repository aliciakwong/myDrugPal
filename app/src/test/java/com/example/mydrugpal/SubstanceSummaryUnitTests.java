package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the SubstanceSummaryActivity and SubstanceSummaryInformation class
 *
 * @author Megan Brock, Alicia Wong
 */
public class SubstanceSummaryUnitTests
{
    /**
     * Tests that the summary information returns
     */
    @Test
    public void TestSubstanceSummaryInformation()
    {
        SubstanceSummaryInformation info = new SubstanceSummaryInformation();

        assertNotNull(info.getSubstanceList());
    }
}
