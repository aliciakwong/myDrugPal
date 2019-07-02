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

import com.example.mydrugpal.model.InfoPage;
import com.example.mydrugpal.viewholder.SubstanceViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 *  Main detail page class which displays the RecyclerView list of substances
 *  and the add substance button
 *
 * @author Emma Travers, Richard Purcell, Ian Sifton
 *
 */
public class SubstanceListActivity extends LogoutActivity {

    private RecyclerView recyclerView;
    private Button addSubstanceButton;

    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    /**
     * Method which sets the content view for this page and creates an OnClickListener method
     * which allows the user to travel to the Add Substance page when they want to add a
     * substance
     *
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        getLayoutInflater().inflate(R.layout.activity_detailpage, contentFrameLayout);

        recyclerView = findViewById(R.id.substanceList);
        addSubstanceButton = findViewById(R.id.addSubstance);
        database = FirebaseFirestore.getInstance();

        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);

        /**
         *
         * Method which sets an OnClickListener button so that the User can travel to the
         * Add Substance page so that they can create and add a new susbtance to the FireStore
         * database
         */
        addSubstanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubstanceListActivity.this, AddCustomSubstanceActivity.class);
                startActivity(intent);
            }
        });

    }


    /**
     * Method which sets up the RecyclerView for the page
     *
     * @param rv (RecyclerView object)
     * @param adapter
     *
     */
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }


    /**
     * Method which sets up the FireStore Recycler adapter, which builds the FireStore Recycler options
     * and sets an OnBindViewHolder and onCreateViewHolder method
     *
     * @param db (FireStore database)
     * @return adapter FirestoreRecyclerAdapter
     */
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("substances").orderBy("substanceName").limit(50);
        FirestoreRecyclerOptions<InfoPage> options = new FirestoreRecyclerOptions.Builder<InfoPage>()
                .setQuery(query,InfoPage.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<InfoPage,SubstanceViewHolder>(options)
        {
            /**
             * nested method which connects each database item to the current view of the page
             *
             * @param holder (SubstanceViewHolder)
             * @param position (integer)
             * @param model (InfoPage)
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
             * @param group (ViewGroup)
             * @param recyclerViewNum (integer)
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
     * required as an extension of logout superclass
     * @return layoutResourceId
     */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detailpage;

    }

}
