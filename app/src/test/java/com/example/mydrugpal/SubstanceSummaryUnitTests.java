package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

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

    /**
     * Tests that both date parsing functions result
     * in same date for same input values of different type.
     */
    @Test
    public void TestParseDate()
    {
        String d1 = "2019-07-12T12:34:25-0700";
        int[] d2 = {2019, 7, 12};

        Date date1 = SubstanceSummaryInformation.parseDate(d1);
        Date date2 = SubstanceSummaryInformation.parseDate(d2);

        assertEquals(date1.getTime(), date2.getTime());
    }
}
