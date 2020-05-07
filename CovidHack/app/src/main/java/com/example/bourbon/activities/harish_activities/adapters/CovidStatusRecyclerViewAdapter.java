package com.example.bourbon.activities.harish_activities.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.CovidStatusCardBinding;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import print.Print;

public class CovidStatusRecyclerViewAdapter extends RecyclerView.Adapter<CovidStatusRecyclerViewAdapter.MyViewHolder> {


        private ArrayList<CovidStatus> productDetails;
        private Print p;
        Context context ;

        public CovidStatusRecyclerViewAdapter(ArrayList<CovidStatus> productDetails){
            this.productDetails = productDetails;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CovidStatusCardBinding binding= DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.covid_status_card,
                    parent,false
            );

            p=new Print(parent.getContext());
            context = parent.getContext();

            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.binding.setPd(productDetails.get(position));
//            p.sprintf("DistrictName: "+productDetails.get(position).getDistrictName());

        }

        @Override
        public int getItemCount() {
            return productDetails.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            CovidStatusCardBinding binding;

            @SuppressLint("MissingPermission")
            private MyViewHolder(@NonNull CovidStatusCardBinding itemView) {
                super(itemView.getRoot());
                binding=itemView;


            }


        }



    }
