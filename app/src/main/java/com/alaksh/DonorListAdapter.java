package com.alaksh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonorListAdapter extends RecyclerView.Adapter<DonorListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Details> donor;

    public DonorListAdapter(Context context, ArrayList<Details> donor) {
        this.context = context;
        this.donor = donor;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       return new DonorListAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_donor_rows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     holder.tvName.setText(donor.get(position).getName());
        holder.tvbloodGroup.setText(donor.get(position).getBloodgroup());
        holder.tvLocation.setText(donor.get(position).getLocation());
        holder.tvPhone.setText(donor.get(position).getPhone_number());

    }

    @Override
    public int getItemCount() {
        return donor.size();
    }

    public void filterList(ArrayList<Details> filterdItems) {
        this.donor = filterdItems;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvbloodGroup, tvLocation, tvPhone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvbloodGroup = itemView.findViewById(R.id.tvbloodGroup);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPhone = itemView.findViewById(R.id.tvPhone);


        }
    }
}
