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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * Activity for logout button and logout functionality
 * @author Emma Travers, Alicia Wong
 *
 */
public abstract class LogoutActivity extends AppCompatActivity {

    /**
     * creates main display activity when called
     * @param savedInstanceState current app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        FloatingActionButton logoutButton = findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                gotoLoginPage();
            }

        });

    }


    private void gotoLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    protected abstract int getLayoutResourceId();
}
