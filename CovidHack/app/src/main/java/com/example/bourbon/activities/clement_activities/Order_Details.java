package com.example.bourbon.activities.clement_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Order_Details extends AppCompatActivity {

    @BindView(R.id.dop)
    TextView dop;
    @BindView(R.id.orderid)
    TextView orderid;
    @BindView(R.id.custname)
    TextView custname;
    Order order;
    AlertDialog.Builder builder;
    private String[] groceries;
    DatabaseReference databaseReference ;
    ArrayList<String> groceryList;
    String uri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__details);
        ButterKnife.bind(this);

        order = (Order) getIntent().getSerializableExtra("MyClass");
        dop.setText(order.getDOP());
        custname.setText(order.getCustName());
        databaseReference = FirebaseDatabase.getInstance().getReference();


    }

    @OnClick(R.id.viewcart)
    public void onViewClicked() {
        if(order.getManuel().equals("False")){
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    Toast.makeText(Order_Details.this,dataSnapshot.child("Carts").child(order.getDOP()).getValue().toString(), Toast.LENGTH_SHORT).show();
                    groceryList = new ArrayList<>();
                    for(DataSnapshot postSnapShot : dataSnapshot.child("Carts").child(order.getDOP()).child("Items").getChildren()){

                        groceryList.add(postSnapShot.getValue().toString());
                    }
                    groceries = groceryList.toArray(new String[groceryList.size()]);
                    builder = new AlertDialog.Builder(Order_Details.this);
                    builder.setTitle("Cart");
                    builder.setItems(groceries, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }

                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Order_Details.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }else{

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    uri = dataSnapshot.child("Carts").child(order.getDOP()).child("imageUri").getValue().toString();
                    ImageView imageView = new ImageView(Order_Details.this);
//                    Glide.with(Order_Details.this).load(uri).into(imageView);
                    Picasso.get().load(uri).into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            builder = new AlertDialog.Builder(Order_Details.this);
                            builder.setTitle("Cart");
                            builder.setView(imageView);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(Order_Details.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
//                    Toast.makeText(Order_Details.this, "Image Downloaded", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Order_Details.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
