package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * Controller for first page display
 * @author Ian Sifton, Alicia Wong, Jocelyn MacDonald
 *
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * creates main display activity when called
     * @param savedInstanceState current app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference loginCollection = database.collection("Users");
        loginCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            /**
             * method called to retrieve users from database and update UserList instance with users
             * @param task task to ensure database is properly accessed and data retrieved
             */
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

        Button registrationButton = findViewById(R.id.button_register);
        registrationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                gotoRegistration();
            }
        });
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
    }

    private void gotoRegistration()
    {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}