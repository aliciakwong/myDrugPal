package com.example.mydrugpal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Verify;
import com.google.firebase.firestore.CollectionReference;


import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class VerifyLogin {

    /**
     * @author: Ian Sifton, Alicia Wong, Jocelyn MacDonald
     *
     * The method checks the email and password parameters to see
     * if they match an existing key and its password.
     * Return true if this passes or return false otherwise
     *
     * @param email String
     * @param password final String
     *
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
