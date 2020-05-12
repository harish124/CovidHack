package com.example.bourbon.activities.harish_activities.model

import android.content.Context
import android.content.Intent
import com.example.bourbon.activities.arumugam_activities.MapsActivity
import com.example.bourbon.activities.arumugam_activities.MapsActivityGeofencing
import com.example.bourbon.activities.clement_activities.*
import com.example.bourbon.activities.harish_activities.PersonLocAct
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CovidStatusInfo
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CustomerOrderInfo
import com.example.bourbon.activities.harish_activities.recycler_view_acts.InfectedPeopleInfo
import com.google.firebase.auth.FirebaseAuth
import frame_transition.Transition
import print.Print

class ActivityNames(private val transition: Transition, val p:Print,val ctx:Context) {

    val arr= arrayOf(MapsActivity::class.java,
    Upi_payments::class.java,
    CovidStatusInfo::class.java,
    MapsActivityGeofencing::class.java,
    YoutubeList::class.java,
    EmergencyContactInfo::class.java,
            Store_Menu::class.java,
            CustomerOrderInfo::class.java,
            Volunteer_Registration::class.java,
            Donation::class.java,
            InfectedPeopleInfo::class.java,
            PersonLocAct::class.java,
            E_Pass::class.java,
            Startact::class.java)

    fun nextActivity(cname:Class<*>){
        transition.goTo(cname)
    }

    fun nextActivity2(cname:Class<*>){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(ctx, cname)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ctx.startActivity(intent)

    }



}