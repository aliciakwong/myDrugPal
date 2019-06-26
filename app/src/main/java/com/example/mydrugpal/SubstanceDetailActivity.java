package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.FirebaseFirestore;

import com.example.mydrugpal.model.InfoPage;


/**
 * Class which displays the details for a particular substance (which the user would have determined
 * in the DetailPageActivity page)
 *
 * @author Emma Travers, Richard Purcell, Ian Sifton
 *
 */
public class SubstanceDetailActivity extends AppCompatActivity {
    private TextView substanceNameView;
    private TextView substanceTypeView;
    private TextView amountView;

    private FirebaseFirestore database;
    private Intent intent;
    private InfoPage infoPage;

    private Button addToIntakeButton;

    /**
     * Method which sets the content view for this page (from the substance_detail xml file),
     * gets the instance of the FireStire database, and creates an InfoPage object to get the
     * details of the substance, and uses that data to set the text on the page
     *
     * @param savedInstanceState (Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.substance_detail);

        substanceNameView = findViewById(R.id.substanceNameView);
        substanceTypeView = findViewById(R.id.substanceTypeView);
        amountView = findViewById(R.id.amountView);

        database = FirebaseFirestore.getInstance();
        intent = getIntent();

        infoPage = (InfoPage) intent.getSerializableExtra("substance");

        substanceNameView.setText(infoPage.substanceName);
        substanceTypeView.setText(infoPage.substanceType);
        amountView.setText(infoPage.amount);

        addToIntakeButton = findViewById(R.id.addToDiaryButton);


        addToIntakeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                goToAddToIntakePage();
            }
        });
    }

    private void goToAddToIntakePage() {
        Intent intent = new Intent(this, AddToIntakeDiaryActivity.class);
        startActivity(intent);
    }

}
