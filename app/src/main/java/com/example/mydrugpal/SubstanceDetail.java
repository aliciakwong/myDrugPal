package com.example.mydrugpal;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.R;
import com.example.mydrugpal.model.InfoPage;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SubstanceDetail extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceMainInfo;

    private FirebaseFirestore database;
    private Intent intent;
    private InfoPage infoPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.substance_detail);

        substanceName = findViewById(R.id.substanceName);
        substanceMainInfo = findViewById(R.id.substanceMainInfo);

        database = FirebaseFirestore.getInstance();
        intent = getIntent();

        infoPage = (InfoPage) intent.getSerializableExtra("substance");

        substanceName.setText(infoPage.substanceName);
        substanceMainInfo.setText(infoPage.substanceMainInfo);

    }
}
