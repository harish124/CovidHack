package com.arumugam.geofencing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private TextView loc;
    private GoogleApiClient googleapiClient;
    private Location location;
    private LocationRequest locationRequest;
    private Geofence geofence;
    private GeofencingClient geofenceclient;
    private GeofenceHelper geofenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loc = findViewById(R.id.location);

        //checkingPermissions();

        googleapiClient= new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        geofenceclient = LocationServices.getGeofencingClient(this);
        geofenceHelper = new GeofenceHelper(this);

        Location l = new Location("");
        l.setLatitude(13.044623d);
        l.setLongitude(80.163358d);
        addGeofence(l,10.0f);
    }

    private void addGeofence(Location l, float radius) {

        Geofence geofence = geofenceHelper.getGeofence("my geofence",l, radius, Geofence.GEOFENCE_TRANSITION_DWELL);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();

        geofenceclient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Geofencing", "onSuccess: Geofence Added...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        Log.d("Geofencing", "onFailure: " + errorMessage);
                    }
                });
    }

    private void checkingPermissions()
    {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Permission not granted..!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package",getPackageName(),null);
            intent.setData(uri);
            startActivity(intent);
        }

        if(!checkPlayServices())
        {
            Toast.makeText(this,"Please install Google Play Services.!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            //loc.setText("Location Unkown");
            Toast.makeText(this,"Location is switched off.!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        } else {
            String s="Location : " + location.getLatitude() + location.getLongitude();
            loc.setText(s);
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
    public void onConnected(@Nullable Bundle bundle) {
            checkingPermissions();
            location = LocationServices.FusedLocationApi.getLastLocation(googleapiClient);

            onLocationChanged(location);

            startLocationUpdates();
    }

    private void startLocationUpdates() {

        //checkingPermissions();

        try {

            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(5000);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleapiClient, locationRequest, this);
        }
        catch(Exception e)
        {
            Toast.makeText(this,"Please grant permissions to proceed.!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(googleapiClient!=null)
        {
            googleapiClient.connect();
        }
        else{
            Toast.makeText(this,"Google API is not initialised",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //checkingPermissions();

        if(!googleapiClient.isConnected())
        {
            googleapiClient.connect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(googleapiClient!=null && googleapiClient.isConnected())
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleapiClient,this);
            googleapiClient.disconnect();
        }
    }
}