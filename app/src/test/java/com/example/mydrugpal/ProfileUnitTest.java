package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the creation of a profile
 *
 * @author Megan Brock, Jocelyn MacDonald, Emma Travers
 */
public class ProfileUnitTest
{
    /**
     * Tests that the profile members won't be null
     */
    @Test
    public void profile_NotEmpty()
    {
        Profile profile = new Profile("A", "B", "C", "D");

        assertNotNull(profile.GetFirstName());
        assertNotNull(profile.GetLastName());
        assertNotNull(profile.GetEmail());
        assertNotNull(profile.GetPassword());
    }

    /**
     * Tests that the profile's first name is set correctly
     */
    @Test
    public void firstName_Valid()
    {
        Profile profile = new Profile("A", "B", "C", "D");

        assertEquals(profile.GetFirstName(), "A");
    }

    /**
     * Tests that the profile's last name is set correctly
     */
    @Test
    public void lastName_Valid()
    {
        Profile profile = new Profile("A", "B", "C", "D");

        assertEquals(profile.GetLastName(), "B");
    }

    /**
     * Tests that the profile's email is set correctly
     */
    @Test
    public void email_Valid()
    {
        Profile profile = new Profile("A", "B", "C", "D");

        assertEquals(profile.GetEmail(), "C");
    }

    /**
     * Tests that the profile's password is set correctly
     */
    @Test
    public void password_Valid()
    {
        Profile profile = new Profile("A", "B", "C", "D");

        assertEquals(profile.GetPassword(), "D");
    }
}

