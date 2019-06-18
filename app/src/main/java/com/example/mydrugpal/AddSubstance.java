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

/**
 * @author Emma Travers, Richard Purcell, Ian Sifton
 *
 * Class which takes in User input to create and add a substance to the FireStore database
 * under the substances collection
 */
public class AddSubstance extends AppCompatActivity {

    private TextView substanceName;
    private TextView substanceMainInfo;

    private FirebaseFirestore database;
    private Button addSubstance;

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param savedInstanceState
     *
     * Method which sets the content view to the activity_add_substance xml page,
     * takes the name and main info of the created substance from the textview items,
     * and creates an onClick method which determines what to do with that data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_substance);

        substanceName = findViewById(R.id.substanceName);
        substanceMainInfo = findViewById(R.id.substanceMainInfo);
        addSubstance = findViewById(R.id.addSubstance);

        database = FirebaseFirestore.getInstance();

        /**
         * @author Emma Travers, Richard Purcell, Ian Sifton
         *
         * nested method which creates an onClickListener button where once the add substance
         * button is clicked, an InfoPage object for this substance is created and then
         * creates a document in the FireStore substances collection using the InfoPage object
         * (which has the inputted name and main details from the User)
         */
        addSubstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoPage c = new InfoPage(substanceName.getText().toString(),
                        substanceMainInfo.getText().toString());

                DocumentReference ref = database.collection("substances").document();
                c.id = ref.getId();
                ref.set(c);

                finish();
            }
        });

    }


}
