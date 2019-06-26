package com.example.mydrugpal;
import java.io.Serializable;

/**
 * Profile object for each user
 *
 * @author Megan Brock, Jocelyn MacDonald, Emma Travers, Alicia Wong
 */
public class Profile implements Serializable
{
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    /**
     * default constructor used for CurrentUser subclass
     */
    public Profile(){

    }
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

    /**
     * set first name
     * @param firstName first name of user
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     * set last name of user
     * @param lastName last name of user
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * set email of user
     * @param email email address of user
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * set user password
     * @param password password of user
     */
    public void setPassword(String password) {
        Password = password;
    }
}