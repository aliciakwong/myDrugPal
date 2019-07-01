package com.example.mydrugpal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.DrugList;
import com.example.mydrugpal.model.InfoPage;
import com.example.mydrugpal.model.IntakeDiaryEntry;
import com.example.mydrugpal.model.MakeIntakeEntry;
import com.example.mydrugpal.model.UserList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AddToIntakeDiaryActivity extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceType;
    private String substanceId;
    private TextView amount;
    private TextView amountPerDoseView;

    private Button addSubstanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_intake_diary);


        substanceName = findViewById(R.id.nameOfSubstanceEdit);
        substanceType = findViewById(R.id.typeOfSubstanceEdit);
        amount = findViewById(R.id.amountEdit);
        amountPerDoseView = findViewById(R.id.amountPerDoseView);

        substanceId = getIntent().getStringExtra("id");

        setPageText();

    }

    /**
     * sets the default to display on the page. The default values are taken from the substance database
     */
    public void setPageText() {
        String name = MakeIntakeEntry.getSubstanceName(substanceId);
        String type = MakeIntakeEntry.getType(substanceId);
        String amountPerDose = MakeIntakeEntry.getAmount(substanceId);

        substanceName.setText(name);
        substanceType.setText(type);
        amountPerDoseView.setText("One dose is: " + amountPerDose);

    }




}