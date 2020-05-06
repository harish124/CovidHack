package com.example.bourbon.activities.arumugam_activities;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bourbon.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity
        implements
        OnMapReadyCallback,
        LocationListener {
    private BottomSheetBehavior<View> behavior;
    private GoogleMap mMap;
    private Location location;
    private MarkerOptions currmarker;
    private ArrayList<HospitalDetails> results;
    private Button searchbutton;
    private ArrayList<MarkerOptions> m;
    private Spinner sp;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    private  LocationManager locationManager;
    private int checked;
    private Button hospital;
    private Button pharmacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_arumugam);

        //setting radio
        checked=1;
        hospital= findViewById(R.id.search_hospital);
        pharmacy = findViewById(R.id.search_pharmacy);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchbutton = (Button) findViewById(R.id.search);

        sp = (Spinner) findViewById(R.id.category);
        String[] options = {"Hospital", "Pharmacy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MapsActivity.this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

       locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);

        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);

        final LinearLayout inner = findViewById(R.id.linear1);

        inner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                inner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                View hidden = inner.getChildAt(inner.getChildCount()-1);

                behavior.setPeekHeight(hidden.getBottom());
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void getHospitals(){
        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    location = mMap.getMyLocation();
                    mMap.clear();

                    if(location==null)
                        return;
//                    currmarker = new MarkerOptions();
//                    LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
//                    currmarker.position(latlng);
//                    currmarker.title("My Location");
//                    currmarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//                    currmarker.snippet("current location");
//                    mMap.addMarker(currmarker);
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,15.0f));

                    results = ApiQuery.ping(location,sp.getSelectedItem().toString().toLowerCase());

                    if(results==null)
                    {
                        Toast.makeText(getApplicationContext(),"Result null",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(results.size()==0)
                        Toast.makeText(getApplicationContext(),"Please try again in few moments",Toast.LENGTH_SHORT).show();

                    m=new ArrayList();

                    for(int i=0;i<results.size();i++)
                    {
                        m.add(new MarkerOptions().position(new LatLng(results.get(i).getLocationlatlng()[0],results.get(i).getLocationlatlng()[1])));
                        m.get(i).snippet(i+"");
                    }

                    if(m==null)
                        return;

                    for(int i=0;i<m.size();i++)
                        mMap.addMarker(m.get(i));

                }
            });
        }
        catch(Exception e)
        {
            Log.d("searchbutton",e.toString());
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        checkingPermissions();

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getHospitals();

            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getSnippet().equals("current location"))
                {
                    Toast.makeText(getApplicationContext(),"My Location",Toast.LENGTH_SHORT).show();
                    return true;
                }



//                Intent intent = new Intent(getApplicationContext(),HospitalView.class);
//                intent.putExtra("obj",results.get(Integer.parsInt(marker.getSnippet())));
                Location l = mMap.getMyLocation();
                StringBuilder sb = new StringBuilder();
                sb.append(l.getLatitude()+","+l.getLongitude());
                String location = sb.toString();
//                intent.putExtra("loc",loc);
//                startActivity(intent);
//                return true;


                HospitalDetails obj = results.get(Integer.parseInt(marker.getSnippet()));
                TextView hospitalname =findViewById(R.id.hospitalname);
                TextView address = findViewById(R.id.address);
                RatingBar rating = findViewById(R.id.rating);
                TextView openinghrs = findViewById(R.id.openinghrs);
                Button directionbutton = findViewById(R.id.directions);

                hospitalname.setText(obj.getHospitalName());
                address.setText(obj.getAddress());
                try {
                    rating.setRating(Float.parseFloat(obj.getRating()));
                }
                catch (Exception e)
                {
                    rating.setVisibility(View.GONE);
                }
                openinghrs.setText(obj.getOpeningHours());

                directionbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String direction="";
                        StringBuilder sb= new StringBuilder();
                        sb.append("https://www.google.com/maps/dir/?api=1");
                        sb.append("&origin="+location);
                        sb.append("&destination="+obj.getLocationlatlng()[0]+","+obj.getLocationlatlng()[1]);

                        direction=sb.toString();

                        Uri uri = Uri.parse(direction);

                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);

                    }
                });
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            return true;
            }
        });
    }
    private boolean checkingPermissions()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                    Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this,"Location Services required",Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }
            //return false;
        }
        else{
            mMap.setMyLocationEnabled(true);
        }

        if(!checkPlayServices())
        {
            Toast.makeText(this,"Please install Google Play Services.!",Toast.LENGTH_SHORT).show();
        }

        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
        if(!lm.isProviderEnabled("gps"))
        {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mMap.setMyLocationEnabled(true);
                } else {
                    Toast.makeText(this,"Cannot provide the location services",Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, 9000);
            } else {
                finish();
            }

            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

        @Override
        public void onLocationChanged(Location location) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
            mMap.animateCamera(cameraUpdate);
            getHospitals();
            locationManager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    public void finderOption(View view) {
        if(view == hospital && checked==2)
        {
            checked=1;
            ((Button)view).setTextColor(getApplication().getResources().getColor(R.color.white));
            view.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
            pharmacy.setTextColor(getApplication().getResources().getColor(R.color.black));
            pharmacy.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
        }
        else  if(view == pharmacy && checked==1)
        {
            checked=2;
            ((Button)view).setTextColor(getApplication().getResources().getColor(R.color.white));
            view.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_selected));
            hospital.setTextColor(getApplication().getResources().getColor(R.color.black));
            hospital.setBackground( getApplication().getResources().getDrawable(R.drawable.rounded_button_unselected));
        }
    }
}
