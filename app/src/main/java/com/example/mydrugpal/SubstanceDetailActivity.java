package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.DrugList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.mydrugpal.model.InfoPage;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.List;


/**
 * Class which displays the details for a particular substance (which the user would have determined
 * in the DetailPageActivity page)
 *
 * @author Emma Travers, Richard Purcell, Ian Sifton, Megan Brock
 *
 */
public class SubstanceDetailActivity extends AppCompatActivity {
    private TextView substanceNameView;
    private TextView substanceTypeView;
    private TextView amountView;

    private FirebaseFirestore database;
    private Intent intent;
    private InfoPage infoPage;

    public TabLayout layout;
    public TabLayout.Tab list;
    public TabLayout.Tab diary;
    public TabLayout.Tab about;

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


        setMenuTabs();

        setTabListener();

        setDrugListListener();

        setViewDetails();

        setUpFirestoreDatabase();

        setAddToIntakeListener();
    }

    /**
     * Change to AddToIntakeDiaryActivity
     */
    private void goToAddToIntakePage() {
        Intent intent = new Intent(this, AddToIntakeDiaryActivity.class);
        intent.putExtra("id", infoPage.id);


        startActivity(intent);
    }

    /**
     * Called when a menu tab is pressed. Changes the activity to
     * the one matching the tab.
     * @param tab A menu tab. Should be list, summary, or about.
     */
    private void changeTab(TabLayout.Tab tab)
    {
        if (tab.getPosition() == 0)
        {
            Intent intent = new Intent(this, SubstanceListActivity.class);
            startActivity(intent);

            System.out.println("List selected");
        }

        else if (tab.getPosition() == 1)
        {
            Intent intent = new Intent(this, SubstanceSummaryActivity.class);
            startActivity(intent);

            System.out.println("Summary selected");
        }

        else if (tab.getPosition() == 2)
        {
            Intent intent = new Intent(this, AboutAppActivity.class);
            startActivity(intent);

            System.out.println("About selected");
        }
    }

    private void setMenuTabs() {
        layout = findViewById(R.id.menuTabLayout);
        list = layout.getTabAt(0);
        diary = layout.getTabAt(1);
        about = layout.getTabAt(2);

        list.select();
    }

    private void setTabListener() {
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTab(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // no action here, needs override definition
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // no action here, needs override definition
            }
        });
    }

    private void setDrugListListener() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference userDoc = database.collection("Users").document(CurrentUser.getInstance().GetEmail());
        CollectionReference drugCollection = userDoc.collection("drugs");
        if (CurrentUser.getInstance() != null && CurrentUser.getInstance().GetEmail() != null && CurrentUser.getInstance().GetEmail() != "") {
            drugCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                /**
                 * method called to retrieve substances from database and update DrugList instance with drugs
                 *
                 * @param task task to ensure database is properly accessed and data retrieved
                 */
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> dList = task.getResult().getDocuments();

                        DrugList.getInstance().updateDrugs(dList);

                    }
                }
            });
        }
    }

    private void setViewDetails() {
        substanceNameView = findViewById(R.id.substanceNameView);
        substanceTypeView = findViewById(R.id.substanceTypeView);
        amountView = findViewById(R.id.amountView);

        addToIntakeButton = findViewById(R.id.addToDiaryButton);
    }

    private void setUpFirestoreDatabase() {
        database = FirebaseFirestore.getInstance();
        intent = getIntent();

        infoPage = (InfoPage) intent.getSerializableExtra("substance");

        if (CurrentUser.getInstance() != null && CurrentUser.getInstance().GetEmail() != null && CurrentUser.getInstance().GetEmail() != "") {
            substanceNameView.setText(infoPage.substanceName);
            substanceTypeView.setText("Type: " + infoPage.substanceType);
            amountView.setText("Recommended amount per dose: " + infoPage.amount);
        }
    }
    private void setAddToIntakeListener() {

        addToIntakeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                goToAddToIntakePage();
            }
        });
    }
}
