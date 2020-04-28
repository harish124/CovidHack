package com.example.bourbon.activities.clement_activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.CallClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Voip extends AppCompatActivity {

    @BindView(R.id.call)
    Button call;
    private static final String APP_KEY = "db1412d3-1ec9-4354-a7b4-05cea4cc42bf";
    private static final String APP_SECRET = "e0BDfr9GtUGBip88hN8fDg==";
    private static final String ENVIRONMENT = "clientapi.sinch.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voip);
        ButterKnife.bind(this);

        Context context = this.getApplicationContext();
        SinchClient sinchClient = Sinch.getSinchClientBuilder().context(context)
                .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost("clientapi.sinch.com")
                .userId("150898")
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.start();
        Toast.makeText(context, "sinch client started", Toast.LENGTH_SHORT).show();

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallClient callClient = sinchClient.getCallClient();
                callClient.callPhoneNumber("+919087376975");
            }
        });

    }


}
