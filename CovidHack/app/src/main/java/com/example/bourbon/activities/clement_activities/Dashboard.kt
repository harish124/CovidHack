package com.example.bourbon.activities.clement_activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.model.ActivityNames
import com.example.bourbon.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import frame_transition.Transition
import print.Print


class Dashboard : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null
    private val transition = Transition(this)
    private val p = Print(this)
    private val mAuth = FirebaseAuth.getInstance()
    private val database= FirebaseDatabase.getInstance()
    private val uid=mAuth.currentUser?.uid.toString() ?: "404"
//    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.pd= ActivityNames(transition,p)
    }

}