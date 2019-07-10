package com.example.mydrugpal.model;

import com.example.mydrugpal.Profile;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.List;

/**
 * Singleton implementation of UserList that holds current users from database
 * @author Jocelyn MacDonald, Alicia Wong
 */
public class UserList {
    private static volatile UserList instance;
    private HashMap<String, Profile> users;

    private UserList(){

    }

    /**
     * Method to get the one static instance of userlist.
     * If userList is not created yet, it will create the one instance
     *
     * @return single static instance of UserList object
     *
     * @see <a href="https://sourcemaking.com/design_patterns/singleton/java/2"></a>
     *
     */

    public static UserList getInstance(){
        if (instance == null) {
            synchronized (UserList.class) {
                if (instance == null) {
                    instance = new UserList();
                }
            }
        }
        return instance;
    }

    /**
     * method updates users hashMap to contain updated list from database
     * @param updatedList list of documentSnapshots from database
     */

    public void updateUsers(List<DocumentSnapshot> updatedList){
        users = new HashMap<>();
        for(DocumentSnapshot d: updatedList){
            String email = d.getId();
            String password = d.get("password").toString();
            String firstName = d.get("firstName").toString();
            String lastName = d.get("lastName").toString();
            Profile currentProfile = new Profile(firstName, lastName, email, password);
            users.put(email, currentProfile);
        }

    }

    /**
     * method returns users in the UserList instance
     * @return HashMap of users emails and passwords
     */
    public HashMap<String, Profile> getUsers(){
        return users;
    }



}
