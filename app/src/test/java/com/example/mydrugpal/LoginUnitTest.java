package com.example.mydrugpal;

import com.google.common.base.Verify;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginUnitTest {

    @Test
    public void testValidLogin(){
        //TODO: need to ensure UserList is populated first
        boolean correctInput = VerifyLogin.validateUser("user@email.com", "password");
        assertTrue(correctInput);

        boolean incorrectInput = VerifyLogin.validateUser("user", "password");
        assertFalse(incorrectInput);

        boolean incorrectInput2 = VerifyLogin.validateUser("user@email.com", "pw");
        assertFalse(incorrectInput2);

    }







}
