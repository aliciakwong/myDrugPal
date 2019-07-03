package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.GetIntakeEntryData;
import com.example.mydrugpal.model.InfoPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Edit intake diary entry activity
 * @author Emma Travers, Ian Sifton
 */
public class EditIntakeDiaryEntry extends AppCompatActivity {

    private EditText substanceName;
    private EditText substanceType;
    private String substanceId;
    private EditText amount;

    private String entryId;


    private EditText entryName;
    private EditText entryType;
    private EditText entryAmount;

    private Button updateEntryButton;
    private Button deleteEntryButton;
    private Intent intent;
    private InfoPage infoPage;

    private static final String TAG = "Intake Entry";

    /**
     * OnCreate method which sets up the content for the edit entry page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intake_diary_entry);

        substanceName = findViewById(R.id.editTextSubstanceName);
        substanceType = findViewById(R.id.editTextSubstanceType);
        amount = findViewById(R.id.editTextSubstanceAmount);
        updateEntryButton = findViewById(R.id.button_saveEntryEdit);
        deleteEntryButton = findViewById(R.id.button_entryDelete);

       // entryId = SubstanceSummaryActivity.summaryInformation.getSubstanceList().get(i).getId();
                //getIntent().getStringExtra("id");

        substanceId = getIntent().getStringExtra("id");


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userRef;

        // IF ELSE statement is utilized to properly run Espresso test for EditIntakeDiaryEntry activity
        // If the CurrentUser is a null object, the app sets up a new profile and points to a specific intake-entry reference
        if (CurrentUser.getInstance().GetEmail() != null) {
            userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());

        } else {
            //Profile p = new Profile("name", "lastname", "Email", "password");
            //CurrentUser.getInstance().setUser(p);
            //userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());
            userRef = database.collection("Users").document("Email");
            substanceId = "6JEJHsp31twgIA9og3vn";
        }

        CollectionReference ref = userRef.collection("IntakeDiary");
        final DocumentReference userEntry = ref.document(substanceId);

        setPageText(userEntry);

        /**
         * OnClickListener method on updateEntry button which will update the Firestore database
         * document with the new information in the corresponding text fields, then sets the intent
         * to the substance summary page in order to return the user to the previous page
         */
        updateEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(userEntry);
            }
        });

        /**
         * OnClickListener method on deleteEntry button which will delete this intake diary entry
         * from the Firestore database, then sets the intent to the substance summary page in order
         * to return the user to the previous page
         */
        deleteEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(userEntry);
            }

        });

    }


    /**
     * Takes the DocumentReference to update the Firestore database with the newly entered information in the page's form
     *
     * @param userEntry for the user's intake diary entry
     */
    public void update(DocumentReference userEntry) {
        entryName = findViewById(R.id.editTextSubstanceName);
        entryType = findViewById(R.id.editTextSubstanceType);
        entryAmount = findViewById(R.id.editTextSubstanceAmount);



        userEntry.update(
                "substanceName", entryName.getText().toString(),
                "type", entryType.getText().toString(),
                "dose", entryAmount.getText().toString()
        );

        Intent intent = new Intent(EditIntakeDiaryEntry.this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }

    /**
     * Takes the DocumentReference to delete the entry in the Firestore database
     *
     * @param userEntry for the user's intake diary entry
     */
    public void delete(DocumentReference userEntry) {
        userEntry.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

        Intent intent = new Intent(EditIntakeDiaryEntry.this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }


    /**
     * Sets the content for the page based on the intake diary entr info taken from the parameter
     *
     * @param userEntry document reference for the particular intake diary entry
     */
    public void setPageText(DocumentReference userEntry) {
        //String name = GetIntakeEntryData.getSubstanceName(substanceId);
        String type = GetIntakeEntryData.getType(substanceId);
        String dose = GetIntakeEntryData.getAmount(substanceId);

        userEntry.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    substanceName.setText(document.getString("substanceName"));
                    substanceType.setText(document.getString("type"));
                    amount.setText(document.getString("dose"));
                }
            }
        });



//        substanceName.setText(name);
//        substanceType.setText(type);
//        amount.setText(dose);
    }
}

