package com.example.mydrugpal;
import java.io.Serializable;

public class Profile implements Serializable
{
    public String firstName;
    public String lastName;
    public String email;
    public String Password;

    /**
     * The sole constructor for Profile objects.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param email The email used to register account
     * @param pass The user's password, stored in plain text
     */
    public Profile (String firstName, String lastName, String email, String pass)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Password = pass;
    }

    /**
     * Accesses the first name of the user
     *
     * @return The first name of the user
     */
    public String GetFirstName()
    {
        return firstName;
    }

    /**
     * Accesses the last name of the user
     *
     * @return The last name of the user
     */
    public String GetLastName()
    {
        return lastName;
    }

    /**
     * Accesses the email of the user
     *
     * @return The email of the user
     */
    public String GetEmail()
    {
        return email;
    }

    /**
     * Accesses the password of the user
     *
     * @return The password of the user
     */
    public String GetPassword()
    {
        return Password;
    }
}