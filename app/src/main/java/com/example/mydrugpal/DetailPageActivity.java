package com.example.mydrugpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.example.mydrugpal.model.InfoPage;
import com.example.mydrugpal.viewholder.SubstanceViewHolder;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.mydrugpal.model.InfoPage;
import com.example.mydrugpal.viewholder.SubstanceViewHolder;

import com.google.firebase.firestore.Query;

/**
 * @author Emma Travers, Richard Purcell, Ian Sifton
 *
 * Main detail page class which displays the RecyclerView list of substances
 * and the add substance button
 */
public class DetailPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button addSubstanceButton;

    private FirebaseFirestore database;
    private FirestoreRecyclerAdapter adapter;

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param savedInstanceState
     *
     * Method which sets the content view for this page and creates an OnClickListener method
     * which allows the user to travel to the Add Substance page when they want to add a
     * substance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage);

        recyclerView = findViewById(R.id.substanceList);
        addSubstanceButton = findViewById(R.id.addSubstance);
        database = FirebaseFirestore.getInstance();

        adapter = setUpAdapter(database);
        setUpRecyclerView(recyclerView,adapter);

        /**
         * @author Emma Travers, Richard Purcell, Ian Sifton
         *
         * Method which sets an OnClickListener button so that the User can travel to the
         * Add Substance page so that they can create and add a new susbtance to the FireStore
         * database
         */
        addSubstanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPageActivity.this, AddSubstance.class);
                startActivity(intent);
            }
        });

    }


    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param rv (RecyclerView object)
     * @param adapter
     *
     * Method which sets up the RecyclerView for the page
     */
    private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
    }


    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param db (FireStore database)
     * @return adapter
     *
     * Method which sets up the FireStore Recycler adapter, which builds the FireStore Recycler options
     * and sets an OnBindViewHolder and onCreateViewHolder method
     */
    private FirestoreRecyclerAdapter setUpAdapter(FirebaseFirestore db)
    {
        Query query = db.collection("substances").orderBy("substanceName").limit(50);
        FirestoreRecyclerOptions<InfoPage> options = new FirestoreRecyclerOptions.Builder<InfoPage>()
                .setQuery(query,InfoPage.class)
                .build();

        FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<InfoPage,SubstanceViewHolder>(options)
        {
            //For each item in the database connect it to the view

            /**
             * @author Emma Travers, Richard Purcell, Ian Sifton
             * @param holder (SubstanceViewHolder)
             * @param position (integer)
             * @param model (InfoPage)
             *
             * nested method which connects each database item to the current view of the page
             */
            @Override
            public void onBindViewHolder(SubstanceViewHolder holder, int position, final InfoPage model)
            {
                holder.substanceName.setText(model.substanceName);

                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetailPageActivity.this, SubstanceDetail.class);
                        intent.putExtra("substance",model);
                        startActivity(intent);
                    }
                });
            }

            /**
             * @author Emma Travers, Richard Purcell, Ian Sifton
             * @param group (ViewGroup)
             * @param i (integer)
             * @return new SubstanceViewHolder
             *
             * nested method which creates a view from the substance_entry xml file
             */
            @Override
            public SubstanceViewHolder onCreateViewHolder(ViewGroup group, int i)
            {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.substance_entry,group,false);
                return new SubstanceViewHolder(view);


            }
        };

        return adapter;

    }


    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     *
     * method which is called every time the activity starts (for this DetailPage activity)
     * and tells the adapter to start listening
     */
    @Override
    protected void onStart() {
        super.onStart();

        adapter.startListening();
    }

    //Method called every time the activity stops

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     *
     * method which is called every time the activity for this page stops and tells the adapter
     * to stop listening
     */
    @Override
    protected void onStop() {
        super.onStop();

        adapter.stopListening();
    }

}
