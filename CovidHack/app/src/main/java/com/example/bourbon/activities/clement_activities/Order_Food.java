package com.example.bourbon.activities.clement_activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Order_Food extends AppCompatActivity {

    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.item_name)
    EditText itemName;
    @BindView(R.id.item_quantity)
    EditText itemQuantity;

    ListView groceryList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> groceries  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_purchase);
        ButterKnife.bind(this);
        View view = LayoutInflater.from(getApplication()).inflate(R.layout.listing_view, null);
        groceryList = findViewById(R.id.grocery_list);
        Bundle b = getIntent().getExtras();
        String name = b.getString("Name");
        storeName.setText(name);
        adapter = new ArrayAdapter<String>(this,R.layout.gro_list,R.id.textViewgroc,groceries);
        groceryList.setAdapter(adapter);
    }

    @OnClick(R.id.add_item)
    public void onViewClicked() {
        groceries.add(itemName.getText().toString() + itemQuantity.getText().toString());
        adapter.notifyDataSetChanged();
    }
}
