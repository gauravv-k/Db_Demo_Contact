package com.gaurav.dbdemo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.dbdemo1.R;
import com.gaurav.dbdemo1.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    // this method says ye card kha se aegega , we will tell the from where the card comes!
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
    return new ViewHolder(view);
    }

    @Override
    // this method refers , what to do when we got the row or card , for every instance !
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhone_number());
    }

    @Override   // How many items ?
    public int getItemCount() {
        return contactList.size();
    }
}








