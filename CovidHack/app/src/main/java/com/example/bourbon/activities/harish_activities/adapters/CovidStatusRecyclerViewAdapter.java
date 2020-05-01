package com.example.bourbon.activities.harish_activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.CovidStatusCardBinding;

import java.util.ArrayList;

import print.Print;

public class CovidStatusRecyclerViewAdapter extends RecyclerView.Adapter<CovidStatusRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<CovidStatus> covidStatuses;
    private Print p;
    void init(Context ctx)
    {
        covidStatuses=new ArrayList<>();
        p=new Print(ctx);
    }

    public CovidStatusRecyclerViewAdapter(ArrayList<CovidStatus> covidStatuses) {
        this.covidStatuses = covidStatuses;
    }

    @NonNull
    @Override
    public CovidStatusRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        init(parent.getContext());

        CovidStatusCardBinding binding=DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.card_harish_emergency_contact_num,
                parent,false
        );

        p.sprintf("Oncreateb");

        return new MyViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull CovidStatusRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.binding.setPd(covidStatuses.get(position));
        p.sprintf("Bind View");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CovidStatusCardBinding binding;
        public MyViewHolder(@NonNull CovidStatusCardBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }
    }
}
