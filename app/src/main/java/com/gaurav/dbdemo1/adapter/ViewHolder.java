package com.gaurav.dbdemo1.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.dbdemo1.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView contactName;
    public TextView phoneNumber;
    public ImageView iconButton;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        contactName = itemView.findViewById(R.id.contactName);
        phoneNumber = itemView.findViewById(R.id.phoneNumber);
        iconButton = itemView.findViewById(R.id.icon_button);

        iconButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("click viewholder", "clicked");
    }
}
