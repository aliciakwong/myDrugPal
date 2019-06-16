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

/**
 * The controller class for the registration page
 *
 * @author Jocelyn MacDonald, Megan Brock, Emma Travers
 */
public class RegistrationActivity extends AppCompatActivity
{
    /**
     * Called when registration activity is created
     *
     *  Finds references to text fields and buttons. Adds listener to register and clear buttons.
     *  Clear button will empty all text fields. Register button will create a profile and add it
     *  to the database if the fields are not empty. Displays a success or failure message.
     *
     * @param savedInstanceState saved state of the app instance
     */
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
            /**
             * on the click of the register button a new profile is created using the text inputted
             * to the text fields on the registration screen and the information is added to the Users
             * collection contained on firestore. The outcome of the addition to firestore outputs an
             * appropriate message telling the user if registration was succeessful or not
             * @param v the registrationActivity page
             */
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
        //config test 3

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             *
             * @param v
             */
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
