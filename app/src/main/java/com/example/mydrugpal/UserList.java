package com.example.mydrugpal;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.List;

public class UserList {
    private static volatile UserList instance;
    private HashMap<String,String> users;

    private UserList(){

    }

    //https://sourcemaking.com/design_patterns/singleton/java/2

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

    public void updateUsers(List<DocumentSnapshot> updatedList){
        users = null;
        for(DocumentSnapshot d: updatedList){
            String email = d.getId();
            String password = d.get("Password").toString();
            users.put(email,password);
        }

    }

    public HashMap<String, String> getUsers(){
        return users;
    }



}
