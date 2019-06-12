package com.example.mydrugpal;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.example.mydrugpal.model.Substances.Acid;
import com.google.firebase.firestore.DocumentReference;

public class AcidInfoActivity extends AppCompatActivity {


    FirebaseFirestore database = FirebaseFirestore.getInstance();
    CollectionReference sub_collection = database.collection("Substances");

    DocumentReference acid = sub_collection.document("Cannabis");

    private Intent intent;

    private TextView acidDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acid);

        acidDesc = findViewById(R.id.textViewAcid);
        database = FirebaseFirestore.getInstance();

        intent = getIntent();


        acidDesc.setText(acid.get().toString());


    }
}
