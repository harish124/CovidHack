package com.example.bourbon.activities.clement_activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.model.Order;
import com.example.bourbon.activities.harish_activities.recycler_view_acts.OrderInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Order_Details extends AppCompatActivity {

    @BindView(R.id.dop)
    TextView dop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__details);
        ButterKnife.bind(this);

        Order order = (Order) getIntent().getSerializableExtra("MyClass");
        dop.setText(order.getDOP());

    }
}
