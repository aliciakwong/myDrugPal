package com.example.mydrugpal;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.example.mydrugpal.model.Substances.Acid;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.StructuredQuery;

public class AcidInfoActivity extends AppCompatActivity {


    FirebaseFirestore database = FirebaseFirestore.getInstance();
    CollectionReference sub_collection = database.collection("Substances");

    DocumentReference acid = sub_collection.document("Cannabis");
    //DocumentSnapshot acid_snap = acid.addSnapshotListener(new DocumentSnapshot());
    //StructuredQuery.FieldReference sub_field = acid.get


    //acid.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>);
    private Intent intent;

    private TextView acidDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acid);

        acidDesc = findViewById(R.id.textViewAcid);
        database = FirebaseFirestore.getInstance();

        intent = getIntent();

        sub_collection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

            @Override
            //need to put a method which will set the text of the Acid TextView


        });

        //need to get field text

        acidDesc.setText(acid.getId());


    }
}
