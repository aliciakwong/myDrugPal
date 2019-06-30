package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class EditIntakeDiaryEntry extends AppCompatActivity {

    private TextView entryName;
    private TextView substanceMainInfo;

    private FirebaseFirestore database;
    private Button updateEntry;
    private Button deleteEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intake_diary_entry);

        entryName = findViewById(R.id.substanceName);
        substanceMainInfo = findViewById(R.id.substanceMainInfo);
        updateEntry = findViewById(R.id.button_saveEntryEdit);
        deleteEntry = findViewById(R.id.button_entryDelete);

        database = FirebaseFirestore.getInstance();



        updateEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditIntakeDiaryEntry.this, SubstanceSummaryActivity.class);
                startActivity(intent);
            }
        });


        deleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditIntakeDiaryEntry.this, SubstanceSummaryActivity.class);
                startActivity(intent);
            }
        });
    }

}

