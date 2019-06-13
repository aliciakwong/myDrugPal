package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.*;

public class RegistrationActivity extends AppCompatActivity
{
    final EditText editFName = findViewById((R.id.editText_fName));
    final EditText editLName = findViewById((R.id.editText_lName));
    final EditText editEmail = findViewById((R.id.editText_Email));
    final EditText editPass = findViewById((R.id.editText_Password));

    final Button registerButton = findViewById(R.id.register_button);
    final Button clearButton = findViewById(R.id.clear_button);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
               registerProfile();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                clearFields();
            }
        });
    }

    // should ret null if unable to create profile, ret profile if created
    public Profile registerProfile()
    {
        Profile p = new Profile(editFName.getText().toString(),
                editLName.getText().toString(),
                editEmail.getText().toString(),
                editPass.getText().toString());

        return p;
    }

    public void clearFields()
    {
        editFName.setText("");
        editLName.setText("");
        editEmail.setText("");
        editPass.setText("");
    }
}
