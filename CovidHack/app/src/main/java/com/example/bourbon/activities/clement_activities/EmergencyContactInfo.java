package com.example.bourbon.activities.clement_activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bourbon.R;
import com.example.bourbon.activities.arumugam_activities.MapsActivity;
import com.example.bourbon.activities.clement_activities.adapter.ProductRecyclerViewAdapter;
import com.example.bourbon.activities.clement_activities.model.ProductDetails;
import com.example.bourbon.databinding.RvHarishEmergencyContactNumBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import print.Print;

public class EmergencyContactInfo extends Activity {
    private FusedLocationProviderClient flpc;
    private RvHarishEmergencyContactNumBinding binding;
    private ArrayList<ProductDetails> products =new ArrayList<>();
    private ProductRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;


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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding= DataBindingUtil.setContentView(EmergencyContactInfo.this, R.layout.rv_harish_emergency_contact_num);
        configRecyclerView();
        flpc = LocationServices.getFusedLocationProviderClient(this);
        fetchProdFromFirebase();
        checkingPermissions();
    }

    void fetchProdFromFirebase(){
        //clement complete this function
        //this.products=prod fetched from firebase;

        flpc.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                try {
                    if (location != null) {
                        Toast.makeText(getApplicationContext(), location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (addresses.size() > 0) {
                            Toast.makeText(getApplicationContext(), addresses.get(0).getSubAdminArea() + "", Toast.LENGTH_SHORT).show();
                            String district = addresses.get(0).getSubAdminArea();


                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    if (snapshot.child("toll-free").hasChild(district)) {
                                        // run some code

                                        String emergency = snapshot.child("toll-free").child(district).child("emergency").getValue().toString();
                                        String landline = snapshot.child("toll-free").child(district).child("landline").getValue().toString();
                                        ProductDetails pd=new ProductDetails(district,emergency,landline);
                                        products.add(pd);
                                        adapter.notifyDataSetChanged();

                                    } else {
                                        Toast.makeText(EmergencyContactInfo.this, "Contact Info Not Found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Log.d("loc", e.toString());
                }
            }

        });



    }

    private void checkingPermissions()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this,"Location Services required",Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},100);
            }
            //return false;
        }
        else{
            Print p =new Print(this);
//            p.sprintf("Permission Already Granted");
        }
    }


}