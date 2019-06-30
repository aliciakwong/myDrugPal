package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SubstanceSummaryActivity extends AppCompatActivity {

    private Button editEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substance_summary);


        editEntryButton = findViewById(R.id.editEntry);

        editEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceSummaryActivity.this, EditIntakeDiaryEntry.class);
                startActivity(intent);
            }
        });
    }



}

