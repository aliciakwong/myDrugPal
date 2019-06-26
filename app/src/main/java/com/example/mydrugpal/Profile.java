package com.example.mydrugpal;
import java.io.Serializable;

/**
 * Profile object for each user
 *
 * @author Megan Brock, Jocelyn MacDonald, Emma Travers
 */
public class Profile implements Serializable
{
    public String FirstName;
    public String LastName;
    public String Email;
    public String Password;

    /**
     * The sole constructor for Profile objects.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param email The Email used to register account
     * @param pass The user's password, stored in plain text
     */
    public Profile (String firstName, String lastName, String email, String pass)
    {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        Password = pass;
    }

    /**
     * Accesses the first name of the user
     *
     * @return The first name of the user
     */
    public String GetFirstName()
    {
        return FirstName;
    }

    /**
     * Accesses the last name of the user
     *
     * @return The last name of the user
     */
    public String GetLastName()
    {
        return LastName;
    }

    /**
     * Accesses the Email of the user
     *
     * @return The Email of the user
     */
    public String GetEmail()
    {
        return Email;
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

    /**
     * checks if any fields are empty
     * @return true if all fields are valid
     */
    public boolean NoNullOrEmptyFields()
    {
        return (FirstName != null && FirstName.length() > 0 &&
                LastName != null && LastName.length() > 0 &&
                Email != null && Email.length() > 0 &&
                Password != null && Password.length() > 0);
    }


}