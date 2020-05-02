package com.example.bourbon.activities.harish_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bourbon.R;
import com.example.bourbon.activities.arumugam_activities.MapsActivity;
import com.example.bourbon.activities.clement_activities.Donation;
import com.example.bourbon.activities.clement_activities.EmergencyContactInfo;
import com.example.bourbon.activities.clement_activities.Upi_payments;
import com.example.bourbon.activities.clement_activities.Volunteer_Registration;
import com.example.bourbon.activities.clement_activities.YoutubeList;
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CovidStatusInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import frame_transition.Transition;
import print.Print;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.hospital)
    ImageView hospital;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.fund)
    ImageView fund;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.lab)
    ImageView lab;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.hotspot)
    ImageView hotspot;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.course)
    ImageView course;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.toll)
    ImageView toll;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.volunteer)
    ImageView volunteer;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.donation)
    ImageView donation;
    @BindView(R.id.linearLayout4)
    LinearLayout linearLayout4;
    private Print p;
    private Transition transition;

    void init() {
        p = new Print(MainActivity.this);
        transition = new Transition(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        p.sprintf("Hello");
    }


    @OnClick({R.id.hospital, R.id.fund, R.id.lab, R.id.hotspot, R.id.course, R.id.toll, R.id.volunteer, R.id.donation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hospital:
                transition.goTo(MapsActivity.class);
                break;
            case R.id.fund:
                transition.goTo(Upi_payments.class);
                break;
            case R.id.lab:
                transition.goTo(CovidStatusInfo.class);
                break;
            case R.id.hotspot:
                break;
            case R.id.course:
                transition.goTo(YoutubeList.class);
                break;
            case R.id.toll:
                transition.goTo(EmergencyContactInfo.class);
                break;
            case R.id.volunteer:
                transition.goTo(Volunteer_Registration.class);
                break;
            case R.id.donation:
                transition.goTo(Donation.class);
                break;
        }
    }


}
