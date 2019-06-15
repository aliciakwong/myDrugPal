package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileUnitTest
{
    /**
     * Tests that the profile members won't be null
     */
    @Test
    public void profile_NotEmpty()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertNotNull(p.GetFirstName());
        assertNotNull(p.GetLastName());
        assertNotNull(p.GetEmail());
        assertNotNull(p.GetPassword());
    }

    /**
     * Tests that the profile's first name is set correctly
     */
    @Test
    public void firstName_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetFirstName(), "A");
    }

    /**
     * Tests that the profile's last name is set correctly
     */
    @Test
    public void lastName_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetLastName(), "B");
    }

    /**
     * Tests that the profile's email is set correctly
     */
    @Test
    public void email_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetEmail(), "C");
    }

    /**
     * Tests that the profile's password is set correctly
     */
    @Test
    public void password_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetPassword(), "D");
    }
}

