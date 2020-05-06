package com.example.bourbon.activities.clement_activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import print.Print;

public class Order_Food extends AppCompatActivity {

    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.item_name)
    EditText itemName;
    @BindView(R.id.item_quantity)
    EditText itemQuantity;

    ListView groceryList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> groceries = new ArrayList<String>();
    private String[] groceries1 ;
    AlertDialog.Builder builder ;
    Print p = new Print(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_purchase);
        ButterKnife.bind(this);
        View view = LayoutInflater.from(getApplication()).inflate(R.layout.listing_view, null);
        groceryList = view.findViewById(R.id.grocery_list);
        builder = new AlertDialog.Builder(Order_Food.this);
        builder.setTitle("Cart");
        Bundle b = getIntent().getExtras();
        String name = b.getString("Name");
        storeName.setText(name);
        adapter = new ArrayAdapter<String>(this, R.layout.gro_list, R.id.textViewgroc, groceries);
        groceryList.setAdapter(adapter);
    }


    @OnClick({R.id.add_item, R.id.submit_list, R.id.cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_item:
                groceries.add(itemName.getText().toString() + ":  " +  itemQuantity.getText().toString());
                adapter.notifyDataSetChanged();
                groceries1 = groceries.toArray(new String[groceries.size()]) ;
                p.sprintf("Item Added To Cart");

                break;
            case R.id.submit_list:
                break;
            case R.id.cart:
                builder.setItems(groceries1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        groceries.remove(which);
                        groceries1 = groceries.toArray(new String[groceries.size()]) ;
                        p.sprintf("Item Deleted From Cart");
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
    }
}
