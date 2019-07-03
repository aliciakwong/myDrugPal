package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the IntakeDiaryItem class.
 *
 * @author Megan Brock, Alicia Wong
 */
public class IntakeDiaryItemTests
{
    /**
     * Tests that the IntakeDiaryItem constuctor sets values properly.
     */
    @Test
    public void TestIntakeDiaryItem() {
        String id = "1233";
        String name = "Nicotine";
        String type = "Narcotic";
        String dose = "10";
        String dateTime = "2019-01-31:T12:30:00";

        IntakeDiaryItem item = new IntakeDiaryItem(id, name, type, dose, dateTime);

        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(type, item.getType());
        assertEquals(dose, item.getDose());
        assertEquals(dateTime, item.getDateTime());
    }
}
