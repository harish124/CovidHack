package com.example.bourbon.activities.harish_activities.model

import com.example.bourbon.activities.arumugam_activities.MapsActivity
import com.example.bourbon.activities.arumugam_activities.MapsActivityGeofencing
import com.example.bourbon.activities.clement_activities.*
import com.example.bourbon.activities.harish_activities.PersonLocAct
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CovidStatusInfo
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CustomerOrderInfo
import com.example.bourbon.activities.harish_activities.recycler_view_acts.InfectedPeopleInfo
import frame_transition.Transition
import print.Print

class ActivityNames(val transition: Transition,val p:Print) {

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

        transition.goTo(cname)
    }



}