package com.example.mydrugpal.viewholder;

//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.mydrugpal.R;

public class SubstanceViewHolder extends RecyclerView.ViewHolder {

    public TextView substanceName;
    public Button detailsButton;
    //public RecyclerView.ViewHolder view;

    public SubstanceViewHolder (View itemView)
    {
        super(itemView);
        substanceName = itemView.findViewById(R.id.substanceName);
        detailsButton = itemView.findViewById(R.id.goDetails);
    }

}

