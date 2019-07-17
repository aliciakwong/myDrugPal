package com.example.mydrugpal.model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.R;
import com.example.mydrugpal.SubstanceSummaryActivity;
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
 * @author Emma Travers, Ian Sifton, Jocelyn MacDonald, Alicia Wong
 */
public class EditIntakeDiaryEntry extends AppCompatActivity {

    private EditText substanceName;
    private EditText substanceType;
    private String substanceId;
    private EditText amount;

    private EditText entryName;
    private EditText entryType;
    private EditText entryAmount;

    private Button updateEntryButton;
    private Button deleteEntryButton;
    private DocumentReference userRef;


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

        substanceId = getIntent().getStringExtra("id");

        setPageText(findIntakeEntry());


        updateEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * on the click on updateEntry button which will update the intake entry with the new
             * information in the corresponding text fields, then sets the intent to the substance
             * summary page in order to return the user to the previous page.
             * @param v the EditIntakeDiaryEntry page
             */
            public void onClick(View v) {
                update();
                goToSubstanceSummary();
            }
        });

        deleteEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * on the click of deleteEntry button this will delete this intake diary entry, then
             * sets the intent to the substance summary page in order to return the user to the
             * previous page.
             * @param v the EditIntakeDiaryEntry page
             */
            public void onClick(View v) {
                delete();
                goToSubstanceSummary();
            }
        });
    }

    /**
     * Updates the Firestore database with the newly entered information on the page.
     */
    public void update() {
        DocumentReference intakeEntry = findIntakeEntry();

        entryName = findViewById(R.id.editTextSubstanceName);
        entryType = findViewById(R.id.editTextSubstanceType);
        entryAmount = findViewById(R.id.editTextSubstanceAmount);

        intakeEntry.update(
                "substanceName", entryName.getText().toString(),
                "type", entryType.getText().toString(),
                "dose", entryAmount.getText().toString()
        );
    }

    /**
     * finds the specific intake diary entry to edit within the users intake diary, which is found
     * via the substanceId
     * @return returns the DocumentReference corresponding to the intake diary entry to be edited
     */
    public DocumentReference findIntakeEntry() {
        DocumentReference userRef = getUserRef();
        CollectionReference intakeDiaryRef = userRef.collection("IntakeDiary");
        DocumentReference entry = intakeDiaryRef.document(substanceId);
        return entry;
    }

    /**
     * deletes the specific intake diary entry
     */
    public void delete() {
        DocumentReference intakeEntry = findIntakeEntry();
        intakeEntry.delete()
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
    }

    /**
     * changes the activity page from the edit page back to the substance summary page.
     */
    public void goToSubstanceSummary() {
        Intent intent = new Intent(EditIntakeDiaryEntry.this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }

    /**
     * Sets the content for the page based on previous intake diary entry information
     * @param userEntry document reference for the particular intake diary entry
     */
    public void setPageText(DocumentReference userEntry) {
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
    }

    /**
     * finds the DocumentReference for the user. Utilized to properly run Espresso test for
     * EditIntakeDiaryEntry activity. If the CurrentUser is a null object, the app sets up a new
     * profile and points to a specific intake-entry reference.
     * @return the DocumentReference of the user
     */
    public DocumentReference getUserRef() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userRef;

        if (CurrentUser.getInstance().GetEmail() != null) {
            userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());

        } else {
            userRef = database.collection("Users").document("Email");
            substanceId = "6JEJHsp31twgIA9og3vn";
        }
        return userRef;
    }
}

