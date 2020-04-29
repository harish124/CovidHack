package com.example.bourbon.activities.clement_activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.adapter.ProductRecyclerViewAdapter;
import com.example.bourbon.activities.clement_activities.model.ProductDetails;
import com.example.bourbon.databinding.RvHarishEmergencyContactNumBinding;

import java.util.ArrayList;

public class EmergencyContactInfo extends Activity {

    private RvHarishEmergencyContactNumBinding binding;
    private ArrayList<ProductDetails> products =new ArrayList<>();
    private ProductRecyclerViewAdapter adapter;

    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(EmergencyContactInfo.this));
        adapter=new ProductRecyclerViewAdapter(products);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(EmergencyContactInfo.this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(EmergencyContactInfo.this, R.layout.rv_harish_emergency_contact_num);
        configRecyclerView();

        fetchProdFromFirebase();
    }

    void fetchProdFromFirebase(){
        //clement complete this function
        //this.products=prod fetched from firebase;
        for (int i=1;i<5;i++) {
            ProductDetails pd=new ProductDetails("Chennai","1234567890","1234567890");
            products.add(pd);
        }
        adapter.notifyDataSetChanged();
    }
}
