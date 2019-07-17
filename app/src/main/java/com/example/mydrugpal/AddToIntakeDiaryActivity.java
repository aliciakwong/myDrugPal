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

    private Button addSubstanceButton;

    /**
     * Called when AddToIntakeDiaryActivity is used
     *
     * Finds references to text fields and buttons. Adds listener to add button.
     *
     * @param savedInstanceState saved state of the app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_intake_diary);

        findReferences();

        setListeners();

        setPageText();
    }

    private void setListeners()
    {
        addSubstanceButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on the click of the add button a new intake entry is created using the text inputted
             * to the text fields on the registration screen and the information is added to the Users
             * intake diary collection contained on firestore.
             * @param v the AddToIntakeDiaryActivity page
             */
            public void onClick(View v)
            {
                connectToFireBase();
            }
        });
    }

    private void connectToFireBase()
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
            goToSubstanceSummary();

        }
    }

    private void findReferences()
    {
        substanceName = findViewById(R.id.nameOfSubstanceEdit);
        substanceType = findViewById(R.id.typeOfSubstanceEdit);
        amount = findViewById(R.id.amountEdit);
        addSubstanceButton = findViewById(R.id.addSubstancebutton);
        amountPerDoseView = findViewById(R.id.amountPerDoseView);

        substanceId = getIntent().getStringExtra("id");
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
        amountPerDoseView.setText("One dose is: " + amountPerDose);

    }

    /**
     * changes the activity to the substanceSummaryActivity
     */
    public void goToSubstanceSummary() {
        Intent intent = new Intent(this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }
}