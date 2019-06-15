package com.example.mydrugpal;
import java.io.Serializable;

public class Profile implements Serializable
{
    public String firstName;
    public String lastName;
    public String email;
    public String Password;

    public Profile (String firstName, String lastName, String email, String pass)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Password = pass;
    }

    public String GetFirstName()
    {
        return firstName;
    }

    public String GetLastName()
    {
        return lastName;
    }

    public String GetEmail()
    {
        return email;
    }

    public String GetPassword()
    {
        return Password;
    }
}