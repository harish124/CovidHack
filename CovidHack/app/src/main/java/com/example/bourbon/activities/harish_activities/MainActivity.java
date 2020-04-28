package com.example.bourbon.activities.harish_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.Main2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import frame_transition.Transition;
import print.Print;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.GeoFencing)
    Button GeoFencing;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
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


    @OnClick({R.id.GeoFencing, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.GeoFencing:
                break;
            case R.id.btn2:
                transition.goTo(Main2Activity.class);
                p.sprintf("Success");
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }
}
