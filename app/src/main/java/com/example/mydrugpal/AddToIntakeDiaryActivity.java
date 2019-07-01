package com.example.mydrugpal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mydrugpal.model.GetIntakeEntryData;

/**
 * activity to add a specific substance to the users intake diary
 * @author Jocelyn MacDonald, Richard Purcell
 */
public class AddToIntakeDiaryActivity extends AppCompatActivity {
    private TextView substanceName;
    private TextView substanceType;
    private String substanceId;
    private TextView amount;
    private TextView amountPerDoseView;

    private Button addSubstanceButton;

    /**
     * Called when registration activity is created
     *
     *  Finds references to text fields and buttons. Adds listener to register and clear buttons.
     *  Clear button will empty all text fields. Register button will create a profile and add it
     *  to the database if the fields are not empty. Displays a success or failure message.
     *
     * @param savedInstanceState saved state of the app instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_intake_diary);


        substanceName = findViewById(R.id.nameOfSubstanceEdit);
        substanceType = findViewById(R.id.typeOfSubstanceEdit);
        amount = findViewById(R.id.amountEdit);
        amountPerDoseView = findViewById(R.id.amountPerDoseView);
        addSubstanceButton = findViewById(R.id.addSubstancebutton);

        substanceId = getIntent().getStringExtra("id");

        setPageText();
    }

    /**
     * sets the default text to display on the page. The default values are taken from the
     * substance database
     */
    public void setPageText() {
        String name = GetIntakeEntryData.getSubstanceName(substanceId);
        String type = GetIntakeEntryData.getType(substanceId);
        String amountPerDose = GetIntakeEntryData.getAmount(substanceId);

        substanceName.setText(name);
        substanceType.setText(type);
        amountPerDoseView.setText("One dose is: " + amountPerDose);

    }

}