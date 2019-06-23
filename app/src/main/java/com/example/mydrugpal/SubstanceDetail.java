package com.example.mydrugpal;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.R;
import com.example.mydrugpal.model.InfoPage;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * @author Emma Travers, Richard Purcell, Ian Sifton
 *
 * Class which displays the details for a particular substance (which the user would have determined
 * in the DetailPageActivity page)
 */
public class SubstanceDetail extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceMainInfo;

    private FirebaseFirestore database;
    private Intent intent;
    private InfoPage infoPage;

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param savedInstanceState (Bundle)
     *
     * Method which sets the content view for this page (from the substance_detail xml file),
     * gets the instance of the FireStire database, and creates an InfoPage object to get the
     * details of the substance, and uses that data to set the text on the page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.substance_detail);

        //FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        //getLayoutInflater().inflate(R.layout.activity_detailpage, contentFrameLayout);

        substanceName = findViewById(R.id.substanceName);
        substanceMainInfo = findViewById(R.id.substanceMainInfo);

        database = FirebaseFirestore.getInstance();
        intent = getIntent();

        infoPage = (InfoPage) intent.getSerializableExtra("substance");

        substanceName.setText(infoPage.substanceName);
        substanceMainInfo.setText(infoPage.substanceMainInfo);

    }

    //@Override
    //protected int getLayoutResourceId() {
     //   return R.layout.substance_detail;

   // }
}
