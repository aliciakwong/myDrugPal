package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.InfoPage;
import com.example.mydrugpal.model.NewIntakeEntry;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * activity to add a custom substance to the users intake diary
 * @author Jocelyn MacDonald, Emma Travers
 */
public class AddCustomSubstanceActivity extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceType;
    private String substanceId;
    private TextView amount;
    private TextView amountPerDoseEdit;

    private Button addSubstanceButton;
    private Button addToListButton;

    /**
     * Called when AddCustomSubstanceActivity is used
     *
     *  Finds references to text fields and buttons. Adds listener to add button.
     *
     * @param savedInstanceState saved state of the app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_substance);


        substanceName = findViewById(R.id.nameOfSubstanceEdit);
        substanceType = findViewById(R.id.typeOfSubstanceEdit);
        amount = findViewById(R.id.amountEdit);
        amountPerDoseEdit = findViewById(R.id.amountPerDoseEdit);
        addSubstanceButton = findViewById(R.id.addSubstancebutton);
        addToListButton = findViewById(R.id.addToListButton);

        substanceId = getIntent().getStringExtra("id");

        addSubstanceButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on the click of the add button a new intake entry is created using the text inputted
             * to the text fields on the registration screen and the information is added to the Users
             * intake diary collection contained on firestore.
             * @param v the AddCustomSubstanceActivity page
             */
            public void onClick(View v)
            {
             addToIntake();
             addToDrugs();
             goToSubstanceSummary();


            }
        });
        addToListButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             * on the click of the add button a new intake entry is created using the text inputted
             * to the text fields on the registration screen and the information is added to the Users
             * intake diary collection contained on firestore.
             * @param v the AddCustomSubstanceActivity page
             */
            public void onClick(View v)
            {
                addToDrugs();
                goToSubstanceSummary();


            }
        });

    }


    /**
     * changes the activity to the substanceSummaryActivity
     */
    public void goToSubstanceSummary() {
        Intent intent = new Intent(this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }

    /**
     * makes an IntakeEntryObject, the data contained within this object is then added to the intakeDiary
     * contained on firestore
     */
    public void addToIntake() {
        NewIntakeEntry entry = new NewIntakeEntry(substanceName.getText().toString(),
                substanceType.getText().toString(),
                amount.getText().toString());

        if (entry.NoNullOrEmptyFields())
        {
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            DocumentReference userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());
            CollectionReference ref = userRef.collection("IntakeDiary");

            ref.document().set(entry);


        }
    }

    /**
     * adds the custom substance to the users personal custom substance database
     */
    public void addToDrugs() {
        InfoPage custom = new InfoPage(substanceName.getText().toString(), substanceType.getText().toString(), amountPerDoseEdit.getText().toString());
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());
        DocumentReference ref = userRef.collection("drugs").document();
        custom.id = ref.getId();
        ref.set(custom);

    }

}