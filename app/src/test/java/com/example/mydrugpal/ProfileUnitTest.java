package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileUnitTest
{
    @Test
    public void profile_NotEmpty()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertNotNull(p.GetFirstName());
        assertNotNull(p.GetLastName());
        assertNotNull(p.GetEmail());
        assertNotNull(p.GetPassword());
    }

    @Test
    public void firstName_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetFirstName(), "A");
    }

    @Test
    public void lastName_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetLastName(), "B");
    }

    @Test
    public void email_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetEmail(), "C");
    }

    @Test
    public void password_Valid()
    {
        Profile p = new Profile("A", "B", "C", "D");

        assertEquals(p.GetPassword(), "D");
    }
}

