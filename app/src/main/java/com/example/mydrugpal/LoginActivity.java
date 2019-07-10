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

import java.util.List;

/**
 * Controller for first page display
 * @author Ian Sifton, Alicia Wong, Jocelyn MacDonald, Emma Travers
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

                    com.example.mydrugpal.model.UserList.getInstance().updateUsers(uList);
                }
            }
        });

        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            /**
             * listener for login button clicked
             * @param view current application view
             */
            public void onClick(View view){
                boolean validatePassword = VerifyLogin.validateUser(
                        ((EditText)findViewById(R.id.enterEmail)).getText().toString(),
                        ((EditText)findViewById(R.id.enterPassword)).getText().toString());

                if(validatePassword){
                    updateCurrentUser(((EditText)findViewById(R.id.enterEmail)).getText().toString());
                    gotoSubstanceSummary();

                }
                else{
                    ((EditText) findViewById(R.id.enterEmail)).setText("");
                    ((EditText) findViewById(R.id.enterPassword)).setText("");
                }
            }

        });

        Button registrationButton = findViewById(R.id.button_register);
        registrationButton.setOnClickListener(new View.OnClickListener(){
            /**
             * listener for registration button click
             * @param view current application view
             */
            public void onClick(View view){
                gotoAboutApp();
            }
        });

        FloatingActionButton aboutAppButton = findViewById(R.id.AboutAppButton);
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
        Profile currentProfile = com.example.mydrugpal.model.UserList.getInstance().getUsers().get(userEmail);
        CurrentUser.getInstance().setUser(currentProfile);
    }

    private void gotoAboutApp() {
        Intent intent = new Intent(this, AboutAppActivity.class);
        startActivity(intent);
    }



}
