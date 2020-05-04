package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.recycler_view_acts.ShopInfo;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Store_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store__menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.storeregister, R.id.nearbystore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.storeregister:
                Intent intent = new Intent(this, Store_Registration.class);
                startActivity(intent);
                break;
            case R.id.nearbystore:
                Intent intent1 = new Intent(this, ShopInfo.class);
                startActivity(intent1);
                break;
        }
    }


}
