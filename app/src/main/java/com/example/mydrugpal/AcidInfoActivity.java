package com.example.mydrugpal;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.example.mydrugpal.model.Substances.Acid;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;
import com.google.firestore.v1.StructuredQuery;

import java.util.List;

public class AcidInfoActivity extends AppCompatActivity {



    private Intent intent;

    private TextView acidDesc;
    public String field_text = "blah";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acid);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        CollectionReference sub_collection = database.collection("Substances");

        DocumentReference acid = sub_collection.document("Cannabis");


        acid.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshotSnapshot> task) {
                if (task.isSuccessful()) {
                    //field_text = "task successful";
                    //List<DocumentSnapshot> sList = task.getResult().getDocuments();
                    //field_text = sList.get(1).getString("Description");

                    DocumentSnapshot a_snap = task.getResult();
                    if (a_snap != null) {
                        field_text = a_snap.get("Description");
                    }
                    else {
                        field_text = "it's not working";
                    }
                }
            }
        });

        acidDesc = findViewById(R.id.textViewAcid);
        //intent = getIntent();

        //need to get field text
        //String field = acid_snap.get("Description").toString();
        acidDesc.setText(field_text); //currently returns document id "cannabis"


    }
}
