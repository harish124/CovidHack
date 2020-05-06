package com.example.bourbon.activities.harish_activities.recycler_view_acts;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import print.Print;

public class CovidStatusInfo extends Activity {

    private RvActivityCovidStatusBinding binding;
    private ArrayList<CovidStatus> products =new ArrayList<>();
    private CovidStatusRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;
    private Print p;

    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(CovidStatusInfo.this));
        adapter=new CovidStatusRecyclerViewAdapter(products);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(CovidStatusInfo.this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    void init(){
        p=new Print(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding= DataBindingUtil.setContentView(CovidStatusInfo.this, R.layout.rv_activity_covid_status);
        configRecyclerView();

        fetchProdFromFirebase();

    }

    void fetchProdFromFirebase(){
        //clement complete this function
        //this.products=prod fetched from firebase;
//        for(int i=0;i<5;i++){
//            CovidStatus cs=new CovidStatus("Chennai",10,10,10);
//            products.add(cs);
//            adapter.notifyItemInserted(i);
//        }


//        Toast.makeText(this, "Inside Firebase", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.covid19india.org/state_district_wise.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                Toast.makeText(CovidStatusInfo.this, "GOt Response", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("Kerala");
                    JSONObject district = jsonObject1.getJSONObject("districtData");
                    int i = 0 ;
                    for(Iterator<String> iter = district.keys(); iter.hasNext();) {
                        String mycity = iter.next();
//                        Toast.makeText(CovidStatusInfo.this,mycity, Toast.LENGTH_SHORT).show();
                        JSONObject thiscity = district.getJSONObject(mycity);
                        int active = thiscity.getInt("active");
                        int recovered = thiscity.getInt("recovered");
                        int deceased = thiscity.getInt("deceased");
                        CovidStatus cs=new CovidStatus(mycity,active,recovered,deceased);
                        products.add(cs);
                        adapter.notifyItemInserted(i);
                        i++;
//                        Toast.makeText(CovidStatusInfo.this,active + "" + recovered + "" + deceased + "", Toast.LENGTH_SHORT).show();

                    }
//                    JSONObject chennai = city.getJSONObject("Chennai");
//                    int active = chennai.getInt("active");
//                    Toast.makeText(CovidStatusInfo.this, active + "", Toast.LENGTH_SHORT).show();
//                    Log.d("Clementment",active + "");

                }catch (JSONException e){
                    Toast.makeText(CovidStatusInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("CLEMENT",e.toString());
                }

            }
        }, error -> p.fprintf(error.getMessage()));

        requestQueue.add(stringRequest);
//        RequestQueue queue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, "https://api.covid19india.org/state_district_wise.json", null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(CovidStatusInfo.this, "Response Received", Toast.LENGTH_SHORT).show();
//                        JSONObject jsonObject1 = null;
//                        try {
//                            jsonObject1 = response.getJSONObject("Tamil Nadu");
//                            JSONObject city = jsonObject1.getJSONObject("districtData");
//                            JSONObject chennai = city.getJSONObject("Chennai");
//                            int active = chennai.getInt("active");
//                            Toast.makeText(CovidStatusInfo.this, active, Toast.LENGTH_SHORT).show();
//
//                        } catch (JSONException e) {
//                            Toast.makeText(CovidStatusInfo.this,e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                        Toast.makeText(CovidStatusInfo.this,error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//        queue.add(JsonObjectRequest);


    }

}
