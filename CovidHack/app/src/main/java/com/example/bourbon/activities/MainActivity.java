package com.example.bourbon.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    void init() {
        p = new Print(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ButterKnife.bind(this);

        p.sprintf("Hello");
    }
}
