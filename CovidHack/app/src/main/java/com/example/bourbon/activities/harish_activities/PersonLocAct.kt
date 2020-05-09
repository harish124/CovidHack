package com.example.bourbon.activities.harish_activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.model.PersonLocModel
import com.example.bourbon.databinding.ActivityPersonLocHarishBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import frame_transition.Transition
import print.Print

class PersonLocAct : AppCompatActivity() {

    private val p= Print(this)
    private var binding:ActivityPersonLocHarishBinding?=null
    private val mAuth= FirebaseAuth.getInstance()
    private val transition= Transition(this)
    private val database= FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_person_loc_harish)

        binding!!.share.setOnClickListener{
            if(binding!!.infectedBox.isChecked()==true){
                if(binding!!.pushBox.isChecked()==true){
                    shareToFirebase()
                    p.sprintf("Your details will be shared now!")
                }
            }

        }
    }

    fun shareToFirebase(){
        val obj:PersonLocModel=readFromSharedPref()?: PersonLocModel("1/1/2000","Kerala")


        database.getReference("Infected")
                .child(mAuth.currentUser?.uid.toString())
                .setValue(obj)
                .addOnCompleteListener{
                    p.sprintf("Published Successfully")
                }
                .addOnFailureListener{e->
                    p.fprintf("Couldn't publish\nError: ${e.message}")
                    println("Error ${e.message}")
                }
    }

    fun readFromSharedPref():PersonLocModel{
        //clement fill this fn
        return PersonLocModel("1/4/2000","Error")
    }
}
