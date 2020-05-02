package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.arumugam_activities.MainActivity;
import com.example.bourbon.activities.arumugam_activities.MapsActivity;
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CovidStatusInfo;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.hospital, R.id.fund, R.id.lab, R.id.hotspot, R.id.course, R.id.toll,R.id.volunteer, R.id.donation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hospital:
                Intent intent6 = new Intent(Main_menu.this, MapsActivity.class);
                startActivity(intent6);
                break;
            case R.id.fund:
                Intent intent = new Intent(Main_menu.this, Upi_payments.class);
                startActivity(intent);
                break;
            case R.id.lab:
                Intent intent7 = new Intent(Main_menu.this, CovidStatusInfo.class);
                startActivity(intent7);
                break;
            case R.id.hotspot:
                Intent intent8 = new Intent(this,Store_Registration.class);
                startActivity(intent8);
                break;
            case R.id.course:
                Intent intent1 = new Intent(Main_menu.this, YoutubeList.class);
                startActivity(intent1);
                break;
            case R.id.toll:
                Intent intent2 = new Intent(Main_menu.this, EmergencyContactInfo.class);
                startActivity(intent2);
                break;
            case R.id.volunteer:
                Intent intent3 = new Intent(Main_menu.this,Volunteer_Registration.class);
                startActivity(intent3);
                 break;
            case R.id.donation:
                Intent intent4 = new Intent(Main_menu.this,Donation.class);
                startActivity(intent4);
                 break;
        }
    }


}
