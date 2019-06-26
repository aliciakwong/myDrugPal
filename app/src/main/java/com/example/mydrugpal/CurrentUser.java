package com.example.mydrugpal;

/**
 * singleton class for current logged-in user that extends Profile class
 *
 * @author Megan Brock, Alicia Wong
 */
public class CurrentUser extends Profile {
    private static CurrentUser instance = new CurrentUser();


    private CurrentUser() {
    }

    /**
     * gets one instance of currentUser or creates instance if not created
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
