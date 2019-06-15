package com.example.mydrugpal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    /**
     *
     * @author: Ian Sifton, Alicia Wong, Jocelyn MacDonald
     * @
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference loginCollection = database.collection("Users");
        //DocumentReference currentUser = loginCollection.document(email);
        loginCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> uList = task.getResult().getDocuments();

                    UserList.getInstance().updateUsers(uList);
                }
            }
        });

        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                boolean validatePassword = VerifyLogin.validateUser(
                       ((EditText)findViewById(R.id.enterEmail)).getText().toString(),
                        ((EditText)findViewById(R.id.enterPassword)).getText().toString());


                if(validatePassword){
                    gotoLogin();

                }
                else{
                    ((EditText) findViewById(R.id.enterEmail)).setText("");
                    ((EditText) findViewById(R.id.enterPassword)).setText("");
                }
            }

        });
    }

    /**
     * @author Ian Sifton, Alicia Wong
     * Method sets up an intent that begins the new activity -- the LoginSuccessActivity class
     */
    public void gotoLogin() {
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }


}
