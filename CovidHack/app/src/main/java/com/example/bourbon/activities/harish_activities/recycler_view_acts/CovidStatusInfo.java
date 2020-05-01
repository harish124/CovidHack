package com.example.bourbon.activities.harish_activities.recycler_view_acts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.YoutubeList;
import com.example.bourbon.activities.clement_activities.videodetails;
import com.example.bourbon.activities.harish_activities.adapters.CovidStatusRecyclerViewAdapter;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.CovidStatusCardBinding;
import com.example.bourbon.databinding.RvActivityCovidStatusBinding;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import print.Print;

public class CovidStatusInfo extends AppCompatActivity {

    private RvActivityCovidStatusBinding binding;
    private Print p;

    void init() {
        p = new Print(this);
    }

    private ArrayList<CovidStatus> products = new ArrayList<>();
    private CovidStatusRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;

    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CovidStatusRecyclerViewAdapter(products);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.rv_activity_covid_status);
        init();
        Toast.makeText(this, "Triggered", Toast.LENGTH_SHORT).show();
        configRecyclerView();
        fetchDetailsFromFirebase();
    }

    private void fetchDetailsFromFirebase() {



        for (int i = 0; i < 10; i++) {
            CovidStatus covidStatus = new CovidStatus("Chennai" + i, 606, 200, 15);
            products.add(covidStatus);
        }

//        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Inside Firebase", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.covid19india.org/state_district_wise.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(CovidStatusInfo.this, "GOt Response", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("Tamil Nadu");
                    JSONObject city = jsonObject1.getJSONObject("districtData");
                    JSONObject chennai = city.getJSONObject("Chennai");
                    int active = chennai.getInt("active");
                    Toast.makeText(CovidStatusInfo.this, active, Toast.LENGTH_SHORT).show();


                }catch (JSONException e){
                    Toast.makeText(CovidStatusInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("CLEMENT",e.toString());
                }

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
