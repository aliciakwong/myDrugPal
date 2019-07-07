package com.example.mydrugpal;

import com.example.mydrugpal.model.IntakeDiaryItem;
import com.google.firebase.Timestamp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the IntakeDiaryItem class.
 *
 * @author Megan Brock, Alicia Wong
 */
public class IntakeDiaryItemUnitTests
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
        Timestamp dateTime = Timestamp.now();

        IntakeDiaryItem item = new IntakeDiaryItem(id, name, type, dose, dateTime);

        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(type, item.getType());
        assertEquals(dose, item.getDose());
        assertEquals(dateTime, item.getDateTime());
    }
}
