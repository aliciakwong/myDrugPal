package com.example.mydrugpal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.InfoPage;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddToIntakeDiaryActivity extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceType;
    private String substanceId;
    private TextView amount;

    private Button addSubstanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_intake_diary);

        substanceName = findViewById(R.id.nameOfSubstanceEdit);
        substanceType = findViewById(R.id.typeOfSubstanceEdit);
        amount = findViewById(R.id.amountEdit);

    }
}