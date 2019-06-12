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



    public static boolean validateUser(String email, final String password){
        if(UserList.getInstance().getUsers() != null &&
                UserList.getInstance().getUsers().containsKey(email) &&
                UserList.getInstance().getUsers().get(email).equals(password)){
            return true;
        }
        return false;
    }




}
