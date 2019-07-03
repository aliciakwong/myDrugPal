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

        substanceName = findViewById(R.id.editTextSubstanceName);
        substanceType = findViewById(R.id.editTextSubstanceType);
        amount = findViewById(R.id.editTextSubstanceAmount);
        updateEntry = findViewById(R.id.button_saveEntryEdit);
        deleteEntry = findViewById(R.id.button_entryDelete);

       // entryId = SubstanceSummaryActivity.summaryInformation.getSubstanceList().get(i).getId();
                //getIntent().getStringExtra("id");

        substanceId = getIntent().getStringExtra("id");



        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userRef = database.collection("Users").document(CurrentUser.getInstance().GetEmail());
        CollectionReference ref = userRef.collection("IntakeDiary");
        final DocumentReference userEntry = ref.document(substanceId);


        setPageText(userEntry);

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

