package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.GetIntakeEntryData;
import com.example.mydrugpal.model.NewIntakeEntry;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * activity to add a specific substance to the users intake diary
 * @author Jocelyn MacDonald, Richard Purcell
 */
public class AddToIntakeDiaryActivity extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceType;
    private String substanceId;
    private TextView amount;
    private TextView amountPerDoseView;
    private TextView messageView;

    private Button addSubstanceButton;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_intake_diary);


        substanceName = findViewById(R.id.nameOfSubstanceEdit);
        substanceType = findViewById(R.id.typeOfSubstanceEdit);
        amount = findViewById(R.id.amountEdit);
        addSubstanceButton = findViewById(R.id.addSubstancebutton);
        messageView = findViewById(R.id.messageView);

        substanceId = getIntent().getStringExtra("id");

        addSubstanceButton.setOnClickListener(new View.OnClickListener()
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
                NewIntakeEntry entry = new NewIntakeEntry(substanceName.getText().toString(),
                        substanceType.getText().toString(),
                        amount.getText().toString());

                if (entry.NoNullOrEmptyFields())
                {
                    FirebaseFirestore database = FirebaseFirestore.getInstance();
                    DocumentReference userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());
                    CollectionReference ref = userRef.collection("IntakeDiary");

                    ref.document().set(entry);
                    goToSubstanceDetail();

                }

                else
                {

                }
            }
        });

        setPageText();

    }

    /**
     * sets the default text to display on the page. The default values are taken from the
     * substance database
     */
    public void setPageText() {
        String name = GetIntakeEntryData.getSubstanceName(substanceId);
        String type = GetIntakeEntryData.getType(substanceId);
        String amountPerDose = GetIntakeEntryData.getAmount(substanceId);

        substanceName.setText(name);
        substanceType.setText(type);

    }

    public void goToSubstanceDetail() {
        Intent intent = new Intent(this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }

}