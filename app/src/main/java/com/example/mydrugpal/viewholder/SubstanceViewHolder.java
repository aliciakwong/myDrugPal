package com.example.mydrugpal.viewholder;

//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.mydrugpal.R;


/**
 * @author Emma Travers, Richard Purcell, Ian Sifton
 *
 * Class which populates the RecyclerView list susbtanceList
 * in order to show the substances currently on the FireStore database
 */
public class SubstanceViewHolder extends RecyclerView.ViewHolder {

    public TextView substanceName;
    public Button detailsButton;
    //public RecyclerView.ViewHolder view;

    /**
     * @author Emma Travers, Richard Purcell, Ian Sifton
     * @param itemView
     *
     * Method which takes each FireStore database entry in the susbtances collection
     * and sets the name and where the details button goes to for each item
     */
    public SubstanceViewHolder (View itemView)
    {
        super(itemView);
        substanceName = itemView.findViewById(R.id.substanceNameView);
        detailsButton = itemView.findViewById(R.id.goDetails);
    }

}

