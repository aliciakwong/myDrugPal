package com.example.mydrugpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterProfileUnitTests
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
}
