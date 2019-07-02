package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.InfoPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

//import com.example.mydrugpal.model.GetIntakeEntryData;


/**
 * Edit intake diary entry activity
 * @author Emma Travers, Ian Sifton
 */
public class EditIntakeDiaryEntry extends AppCompatActivity {

    private EditText entryName;
    private EditText entryType;
    private EditText entryAmount;

    private FirebaseFirestore database;
    private Button updateEntry;
    private Button deleteEntry;
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

        entryName = findViewById(R.id.editTextSubstanceName);
        entryType = findViewById(R.id.editTextSubstanceType);
        entryAmount = findViewById(R.id.editTextSubstanceAmount);
        updateEntry = findViewById(R.id.button_saveEntryEdit);
        deleteEntry = findViewById(R.id.button_entryDelete);

        database = FirebaseFirestore.getInstance();

        //HARD CODED COLLECTION/DOCUMENT PATHS
        final DocumentReference userDoc = database.collection("Users").document("Email");
        CollectionReference userIntakeDiary = userDoc.collection("IntakeDiary");
        final DocumentReference userEntry = userIntakeDiary.document("pGggvip6Mq8AygPLsCcU");

        /**
         * method to set the intake diary entry information into the corresponding text boxes on the page
         * Currently, this will grab the field data based on the above DocumentReference variable
         */
        userEntry.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();

                entryName.setText(document.getString("substanceName"));
                entryType.setText(document.getString("type"));
                entryAmount.setText(document.getString("dose"));
            }
        });

        /**
         * OnClickListener method on updateEntry button which will update the Firestore database
         * document with the new information in the corresponding text fields, then sets the intent
         * to the substance summary page in order to return the user to the previous page
         */
        updateEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        /**
         * OnClickListener method on deleteEntry button which will delete this intake diary entry
         * from the Firestore database, then sets the intent to the substance summary page in order
         * to return the user to the previous page
         */
        deleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userEntry.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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

        });

    }
}

