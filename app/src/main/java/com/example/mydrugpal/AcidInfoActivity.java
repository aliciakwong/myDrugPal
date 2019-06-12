package com.example.mydrugpal;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mydrugpal.model.Substance;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class AcidInfoActivity extends AppCompatActivity {


    FireBaseFirestore database;
    private Intent intent;
    private Substance substance;

    private TextView acidDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acid);


        database = FirebaseFirestore.getInstance();

        intent = getIntent();

        substance = (Substance)intent.getSerializableExtra("substance");

        acidDesc = findViewByID(R.id.textViewAcid);
        //acidDesc.setText(substance.cannabis.)

    }
}
