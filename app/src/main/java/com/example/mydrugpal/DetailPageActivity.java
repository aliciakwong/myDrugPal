package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import com.example.mydrugpal.model.Substance;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

public class DetailPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage);
    }

    public void goToHeisenberg(View view) {
        Intent intent = new Intent(this, HeisenbergInfoActivity.class);
        startActivity(intent);
    }

    public void goToWhiskey(View view) {
        Intent intent = new Intent(this, WhiskeyInfoRefactor.class);
        startActivity(intent);
    }

    public void goToCannabis(View view) {
        Intent intent = new Intent(this, CannabisInfoActivity.class);
        startActivity(intent);
    }

    public void goToAcid(View view) {
        Intent intent = new Intent(this, AcidInfoActivity.class);
        startActivity(intent);
    }

    public void goToCocaCola(View view) {
        Intent intent = new Intent(this, CocaColaInfoActivity.class);
        startActivity(intent);
    }
}
