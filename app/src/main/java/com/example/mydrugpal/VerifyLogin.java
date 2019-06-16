package com.example.mydrugpal;

/**
 *  Class that holds business logic to verify a user when logging in
 *
 *  @author: Ian Sifton, Alicia Wong, Jocelyn MacDonald
 */
public class VerifyLogin {

    /**
     * The method checks the email and password parameters to see
     * if they match an existing key and its password
     *
     * @return whether user is valid or not
     */
    public static boolean validateUser(String email, final String password){
        if(UserList.getInstance().getUsers() != null &&
                UserList.getInstance().getUsers().containsKey(email) &&
                UserList.getInstance().getUsers().get(email).equals(password)){
            return true;
        }
        return false;
    }




}
