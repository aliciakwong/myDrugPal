package com.example.mydrugpal;
import java.io.Serializable;

/**
 * Profile object for each user
 *
 * @author Megan Brock, Jocelyn MacDonald, Emma Travers, Alicia Wong
 */
public class Profile implements Serializable
{
    /**
     * The first name of the user
     */
    public String firstName;

    /**
     * The last name of the user
     */
    public String lastName;

    /**
     * The email of the user
     */
    public String email;

    /**
     * The password of the user
     */
    public String password;

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
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        password = pass;

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
     * Accesses the Email of the user
     *
     * @return The Email of the user
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
        return password;
    }

    /**
     * checks if any fields are empty
     * @return true if all fields are valid
     */
    public boolean NoNullOrEmptyFields()
    {
        return (firstName != null && firstName.length() > 0 &&
                lastName != null && lastName.length() > 0 &&
                email != null && email.length() > 0 &&
                password != null && password.length() > 0);
    }

    /**
     * set first name
     * @param firstName first name of user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * set last name of user
     * @param lastName last name of user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * set email of user
     * @param email email address of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set user password
     * @param password password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
