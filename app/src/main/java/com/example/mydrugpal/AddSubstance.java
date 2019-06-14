package com.example.mydrugpal;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.R;
import com.example.mydrugpal.model.InfoPage;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddSubstance extends AppCompatActivity {

    private TextView substanceName;
    private TextView substanceMainInfo;

    private FirebaseFirestore database;
    private Button addSubstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_substance);

        substanceName = findViewById(R.id.substanceName);
        substanceMainInfo = findViewById(R.id.substanceMainInfo);
        addSubstance = findViewById(R.id.addSubstance);

        database = FirebaseFirestore.getInstance();

        addSubstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                InfoPage c = new InfoPage(substanceName.getText().toString(),
                        substanceMainInfo.getText().toString());

                //Here instead of adding directly we are first getting a reference so we save the ID;
                // this is not necessary but it will make life easier latter when editing/deleting.
                DocumentReference ref = database.collection("substances").document();
                c.id = ref.getId();
                ref.set(c);

                //Finishes the acitivy and return to the parent one.
                finish();
            }
        });

    }


}
