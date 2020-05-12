package com.example.bourbon.activities.harish_activities

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.model.ActivityNames
import com.example.bourbon.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import frame_transition.Transition
import print.Print
import java.io.IOException
import java.util.*


class Dashboard : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null
    private val transition = Transition(this)
    private val p = Print(this)
    private val mAuth = FirebaseAuth.getInstance()
    private val database= FirebaseDatabase.getInstance()
    private val uid=mAuth.currentUser?.uid.toString() ?: "404"
    private var sharedPreferences:SharedPreferences?=null
    private var locationManager: LocationManager?=null
    private var geocoder:Geocoder?=null

    private val locationRequest=LocationRequest().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    private var fusedLocationProviderClient:FusedLocationProviderClient?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callPermissions()
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.pd= ActivityNames(transition,p,this)
        sharedPreferences= getSharedPreferences("default", Context.MODE_PRIVATE)
        locationManager= (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        geocoder=Geocoder(this)
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

        database.getReference("users")
                .child(mAuth.uid ?: "0fW4KxnDPUgQILoc4SDTT1OwJMn2")
                .addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(user: DataSnapshot) {
                        println("User = ${user}\nName: ${user.child("Name").value}")
                        binding!!.textView.text="Welcome ${user.child("Name").getValue().toString()}"
                    }

                })


    }

    private fun fetchLocation() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        ==PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient!!.requestLocationUpdates(locationRequest,
                    object:LocationCallback(){
                        override fun onLocationResult(locationResult: LocationResult?) {
                            //p.sprintf("Line 2")
                            locationResult ?:return

                            locationResult.locations.forEach { location ->

                                try{
                                    val addr2 = geocoder!!.getFromLocation(location.latitude, location.longitude, 5)?.get(0)
                                            ?: Address(Locale("Tamil"))

                                    println("""
                                            Update
                                            Lat: ${location.latitude}
                                            Long: ${location.longitude} 
                                            Addr: ${addr2.getAddressLine(0)}
                                        """.trimIndent())
                                }
                                catch (e:Exception){
                                    p.fprintf("Error: ${e.message}")
                                }
                            }
                        }

                    }
                    ,Looper.getMainLooper())
        }
        else{
            callPermissions()
        }
    }

    private fun sharedCode(address: String?) {
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putString(Calendar.getInstance().time.toString(), address)
        editor.apply()
        //getShared()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        getLastLocation()
                    }
                }
                else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        getLastLocation()
                    }
                }
            }
        }
    }

    private fun callPermissions(){
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),1000)
    }

    private fun getHomeLocation() {
        try {
            val address = Geocoder(this).getFromLocationName("47/18 Krishnapuram Street" +
                    "Choolaimedu Chennai", 5)[0]
            //            SharedCode(address.toString());
            p.sprintf("Home Lat: " + address.latitude + " Long: " + address.longitude)
            val addr2 = Geocoder(this).getFromLocation(13.060661, 80.228231, 5)[0]
            //            SharedCode(addr2.toString());
            p.sprintf("""
                Your Loc Name: ${addr2.getAddressLine(0)}
                Locality${addr2.locality}
                """.trimIndent())
        } catch (e: java.lang.Exception) {
            p.fprintf("""
                Failed to get Home Lat Long
                Error: ${e.message}
                """.trimIndent())
            e.printStackTrace()
        }
    }
    private fun getLastLocation() {
        fusedLocationProviderClient!!.getLastLocation().addOnSuccessListener(OnSuccessListener { location: Location? ->
            if (location != null) {
                val geocoder = Geocoder(this)
                try {
                    val addr2 = geocoder.getFromLocation(location.latitude, location.longitude, 5)[0]
                    //add this below loc info to shared pref
                    sharedCode(addr2.getAddressLine(0).toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Log.d("Fused Loc ", """
                     Lat: ${location.latitude}
                     Long: ${location.longitude}
                     """.trimIndent())
            }
        }).addOnFailureListener { e: Exception ->
            p.fprintf("""
                        Failed to retrieve Locaiton
                        Error: ${e.message}
                        """.trimIndent())
        }
    }

}