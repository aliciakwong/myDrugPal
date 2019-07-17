package com.example.mydrugpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydrugpal.model.CurrentUser;
import com.example.mydrugpal.model.InfoPage;
import com.example.mydrugpal.viewholder.SubstanceViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 *  Main detail page class which displays the RecyclerView list of substances
 *  and the add substance button
 *
 * @author Emma Travers, Richard Purcell, Ian Sifton, Megan Brock
 *
 */
public class SubstanceListActivity extends LogoutActivity {

    private RecyclerView recyclerView;
    private Button addSubstanceButton;

    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    public TabLayout layout;
    public TabLayout.Tab list;
    public TabLayout.Tab diary;
    public TabLayout.Tab about;

    /**
     * Method which sets the content view for this page and creates an OnClickListener method
     * which allows the user to travel to the Add Substance page when they want to add a
     * substance
     *
     * @param savedInstanceState saved instance state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        getLayoutInflater().inflate(R.layout.activity_detailpage, contentFrameLayout);

        setUpMenuTabs();

        setTabListener();

        setRecyclerView();

        setSubstanceButtonListener();
    }


    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }

    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("Users").document(CurrentUser.getInstance().GetEmail()).collection("drugs").orderBy("substanceName");
        FirestoreRecyclerOptions<InfoPage> options = new FirestoreRecyclerOptions.Builder<InfoPage>()
                .setQuery(query,InfoPage.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<InfoPage,SubstanceViewHolder>(options)
        {
            /**
             * nested method which connects each database item to the current view of the page
             *
             * @param holder the substance view holder page
             * @param position position in the recycler view
             * @param model the info page corresponding to the specific substance
             *
             */
            @Override
            public void onBindViewHolder(SubstanceViewHolder holder, int position, final InfoPage model)
            {
                holder.substanceName.setText(model.substanceName);

                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SubstanceListActivity.this, SubstanceDetailActivity.class);
                        intent.putExtra("substance",model);
                        startActivity(intent);
                    }
                });
            }

            /**
             * nested method which creates a view from the substance_entry xml file
             *
             * @param group the ViewGroup
             * @param recyclerViewNum the number of the drug within the recycler view
             * @return SubstanceViewHolder view holder for substances
             *
             */
            @Override
            public SubstanceViewHolder onCreateViewHolder(ViewGroup group, int recyclerViewNum)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.substance_entry,group,false);
                return new SubstanceViewHolder(view);
            }
        };

        return adapter;
    }


    /**
     * method which is called every time the activity starts (for this DetailPage activity)
     * and tells the adapter to start listening
     */
    @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    /**
     * method which is called every time the activity for this page stops and tells the adapter
     * to stop listening
     */
    @Override
    protected void onStop() {
        super.onStop();

        adapter.stopListening();
    }

    /**
     * Returns to summary page when back button is pressed
     */
    @Override
    public void onBackPressed()
    {
        goToSubstanceSummary();
    }

    /**
     * required as an extension of logout superclass
     * @return layoutResourceId returns the specific detail page
     */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detailpage;
    }

    /**
     * Change activity to SubstanceSummaryActivity
     */
    public void goToSubstanceSummary() {
        Intent intent = new Intent(SubstanceListActivity.this, SubstanceSummaryActivity.class);
        startActivity(intent);
    }

    /**
     * Change activity to AddCustomSubstanceActivity
     */
    public void goToAddSubstance() {
        Intent intent = new Intent(SubstanceListActivity.this, AddCustomSubstanceActivity.class);
        startActivity(intent);
    }

    private void changeTab(TabLayout.Tab tab)
    {
        if (tab.getText().toString().equalsIgnoreCase("List"))
        {
            Intent intent = new Intent(this, SubstanceListActivity.class);
            startActivity(intent);

            System.out.println("List selected");
        }

        else if (tab.getText().toString().equalsIgnoreCase("Summary"))
        {
            Intent intent = new Intent(this, SubstanceSummaryActivity.class);
            startActivity(intent);

            System.out.println("Summary selected");
        }

        else if (tab.getText().toString().equalsIgnoreCase("About"))
        {
            Intent intent = new Intent(this, AboutAppActivity.class);
            startActivity(intent);

            System.out.println("About selected");
        }
    }

    private void setUpMenuTabs() {
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
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.substanceList);
        addSubstanceButton = findViewById(R.id.addSubstance);
        database = FirebaseFirestore.getInstance();

        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);
    }

    private void setSubstanceButtonListener() {
        addSubstanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddSubstance();
            }
        });
    }
}
