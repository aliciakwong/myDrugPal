package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

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

        final TextView messageText = findViewById(R.id.textView_Message);

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Profile p = new Profile(editFName.getText().toString(),
                                        editLName.getText().toString(),
                                        editEmail.getText().toString(),
                                        editPass.getText().toString());

                if (p.GetFirstName() != null && p.GetFirstName().length() > 0 &&
                    p.GetLastName() != null && p.GetLastName().length() > 0 &&
                    p.GetEmail() != null && p.GetEmail().length() > 0 &&
                    p.GetEmail() != null && p.GetEmail().length() > 0)
                {
                    FirebaseFirestore database = FirebaseFirestore.getInstance();
                    DocumentReference ref = database.collection("Users").document(p.GetEmail());
                    ref.set(p);

                    messageText.setText("Profile successfully created!");
                    messageText.setTextColor(Color.parseColor("#49af48"));
                }

                else
                {
                    messageText.setText("Profile could not be created!");
                    messageText.setTextColor(Color.parseColor("#c44040"));
                }
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

                messageText.setText("");
            }
        });
    }
}
