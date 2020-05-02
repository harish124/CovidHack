package com.example.bourbon.activities.harish_activities.recycler_view_acts;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.adapter.ProductRecyclerViewAdapter;
import com.example.bourbon.activities.clement_activities.model.ProductDetails;
import com.example.bourbon.activities.harish_activities.adapters.CovidStatusRecyclerViewAdapter;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.RvActivityCovidStatusBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import print.Print;

public class CovidStatusInfo extends Activity {

    private RvActivityCovidStatusBinding binding;
    private ArrayList<CovidStatus> products =new ArrayList<>();
    private CovidStatusRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;

    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(CovidStatusInfo.this));
        adapter=new CovidStatusRecyclerViewAdapter(products);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(CovidStatusInfo.this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding= DataBindingUtil.setContentView(CovidStatusInfo.this, R.layout.rv_activity_covid_status);
        configRecyclerView();

        fetchProdFromFirebase();

    }

    void fetchProdFromFirebase(){
        //clement complete this function
        //this.products=prod fetched from firebase;
        for(int i=0;i<5;i++){
            CovidStatus cs=new CovidStatus("Chennai",10,10,10);
            products.add(cs);
            adapter.notifyItemInserted(i);
        }


    }

}
