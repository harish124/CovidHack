package com.example.bourbon.activities.harish_activities.recycler_view_acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.adapters.CovidStatusRecyclerViewAdapter;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.CovidStatusCardBinding;
import com.example.bourbon.databinding.RvActivityCovidStatusBinding;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import print.Print;

public class CovidStatusInfo extends AppCompatActivity {

    private RvActivityCovidStatusBinding binding;
    private Print p;

    void init()
    {
        p=new Print(this);
    }

    private ArrayList<CovidStatus> products =new ArrayList<>();
    private CovidStatusRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;

    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CovidStatusRecyclerViewAdapter(products);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.rv_activity_covid_status);
        init();

        configRecyclerView();
        fetchDetailsFromFirebase();
    }

    private void fetchDetailsFromFirebase() {
        //fill this fn
    }
}
