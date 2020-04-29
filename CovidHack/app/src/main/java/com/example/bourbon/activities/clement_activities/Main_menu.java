package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.hospitals, R.id.fund, R.id.labs, R.id.hotspot, R.id.courses, R.id.tollfree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hospitals:
                break;
            case R.id.fund:
                Intent intent = new Intent(Main_menu.this,Upi_payments.class);
                startActivity(intent);
                break;
            case R.id.labs:
                break;
            case R.id.hotspot:
                break;
            case R.id.courses:
                Intent intent1 = new Intent(Main_menu.this,YoutubeList.class);
                startActivity(intent1);
                break;
            case R.id.tollfree:
                Intent intent2 = new Intent(Main_menu.this,EmergencyContactInfo.class);
                startActivity(intent2);
                break;
        }
    }
}
