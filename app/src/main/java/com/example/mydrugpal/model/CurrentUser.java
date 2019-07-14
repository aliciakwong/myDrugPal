package com.example.mydrugpal.model;

import com.example.mydrugpal.Profile;

/**
 * Singleton class for current logged-in user that extends Profile class
 *
 * @author Megan Brock, Alicia Wong
 */
public class CurrentUser extends Profile {
    private static CurrentUser instance = new CurrentUser();

    /**
     * Default constructor for CurrentUser
     */
    private CurrentUser() { }

    /**
     * Gets instance of currentUser or creates instance if not created
     * @return current logged-in user
     */
    public static CurrentUser getInstance(){
        if (instance == null) {
            synchronized (CurrentUser.class) {
                if (instance == null) {
                    instance = new CurrentUser();
                }
            }
        }
        return instance;
    }

    /**
     * sets current user's information after successful login
     * @param profile the current user's information
     */
    public void setUser(Profile profile){
        setEmail(profile.GetEmail());
        setFirstName(profile.GetFirstName());
        setLastName(profile.GetLastName());
        setPassword(profile.GetPassword());
    }
}
