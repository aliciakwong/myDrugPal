package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;



import java.io.*;

public class RegistrationActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText editFName = findViewById((R.id.editText_fName));
        final EditText editLName = findViewById((R.id.editText_lName));
        final EditText editEmail = findViewById((R.id.editText_Email));
        final EditText editPass = findViewById((R.id.editText_Password));

        final Button registerButton = findViewById(R.id.register_button);
        final Button clearButton = findViewById(R.id.clear_button);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Profile p = new Profile(editFName.getText().toString(),
                                        editLName.getText().toString(),
                                        editEmail.getText().toString(),
                                        editPass.getText().toString());


                FirebaseFirestore database = FirebaseFirestore.getInstance();
                DocumentReference ref = database.collection("Users").document(p.GetEmail());
                ref.set(p);


            }
        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                editFName.setText("");
                editLName.setText("");
                editEmail.setText("");
                editPass.setText("");
            }
        });
    }

}
