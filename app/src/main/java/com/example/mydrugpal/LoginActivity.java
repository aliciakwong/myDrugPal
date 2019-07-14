package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.VerifyLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.mydrugpal.model.UserList;


import java.util.List;

/**
 * Controller for first page display
 * @author Ian Sifton, Alicia Wong, Jocelyn MacDonald, Emma Travers
 *
 */
public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText emailText;
    private EditText passText;
    private Button registrationButton;
    private FloatingActionButton aboutAppButton;

    /**
     * creates main display activity when called
     * @param savedInstanceState current app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findReferences();

        connectToFireStore();

        setListeners();
    }

    private void setListeners()
    {
        loginButton.setOnClickListener(new View.OnClickListener(){
            /**
             * listener for login button clicked
             * @param view current application view
             */
            public void onClick(View view){
                validateFields();
            }

        });

        registrationButton.setOnClickListener(new View.OnClickListener(){
            /**
             * listener for registration button click
             * @param view current application view
             */
            public void onClick(View view){
                gotoAboutApp();
            }
        });

        aboutAppButton.setOnClickListener(new View.OnClickListener(){
            /**
             * listener for go to AboutAPP button click
             * @param view current application view
             */
            public void onClick(View view){
                gotoAboutApp();
            }
        });
    }

    private void validateFields()
    {
        boolean validatePassword = VerifyLogin.validateUser(
                emailText.getText().toString(),
                passText.getText().toString());

        if(validatePassword){
            updateCurrentUser(emailText.getText().toString());
            gotoSubstanceSummary();
        }
        else{
            emailText.setText("");
            passText.setText("");
        }
    }

    private void findReferences()
    {
        loginButton = findViewById(R.id.button_login);
        emailText = findViewById(R.id.enterEmail);
        passText = findViewById(R.id.enterPassword);
        registrationButton = findViewById(R.id.button_register);
        aboutAppButton = findViewById(R.id.AboutAppButton);
    }

    private void connectToFireStore()
    {
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
    }

    private void gotoSubstanceSummary() {
        Intent intent = new Intent(this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }

    private void gotoRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    /**
     * when back button is pressed, always close this activity
     */
    @Override
    public void onBackPressed() {
        finish();

    }

    private void updateCurrentUser(String userEmail){
        Profile currentProfile = UserList.getInstance().getUsers().get(userEmail);
        CurrentUser.getInstance().setUser(currentProfile);
    }

    private void gotoAboutApp() {
        Intent intent = new Intent(this, AboutAppActivity.class);
        startActivity(intent);
    }



}
