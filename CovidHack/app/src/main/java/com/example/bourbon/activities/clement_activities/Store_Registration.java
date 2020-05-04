//package com.example.bourbon.activities.clement_activities;
//
//import android.os.Bundle;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.bourbon.R;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import print.Print;
//
//public class Store_Registration extends AppCompatActivity {
//
//
//    EditText storename;
//
//    Spinner storetype;
//
//    EditText storeaddress;
//
//    CheckBox checkadd;
//    FirebaseAuth auth;
//    FirebaseUser user;
//    DatabaseReference mDatabase;
//    @BindView(R.id.pincode)
//    EditText pincode;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stores_main);
//        ButterKnife.bind(this);
//        auth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        storename = findViewById(R.id.storename);
//        storeaddress = findViewById(R.id.storeaddress);
//        storetype = findViewById(R.id.storetype);
//        checkadd = findViewById(R.id.checkadd);
//    }
//
//    @OnClick(R.id.submit)
//    public void onViewClicked() {
//        mDatabase.child("Stores").child(auth.getUid()).child("Name").setValue(storename.getText().toString());
//        mDatabase.child("Stores").child(auth.getUid()).child("Type").setValue(storetype.getSelectedItem().toString());
//
//
//        if (checkadd.isChecked()) {
//            mDatabase.child("users").child(auth.getUid()).child("Address").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    String address = dataSnapshot.getValue(String.class);
////                    Toast.makeText(Store_Registration.this, address, Toast.LENGTH_SHORT).show();
//                    mDatabase.child("Stores").child(auth.getUid()).child("Address").setValue(address);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(Store_Registration.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//        } else {
//            mDatabase.child("Stores").child(auth.getUid()).child("Pincode").setValue(pincode.getText().toString());
//            mDatabase.child("Stores").child(auth.getUid()).child("Address").setValue(storeaddress.getText().toString());
//        }
//
//        Print p = new Print(this);
//        p.sprintf("Store Details Added");
//        finish();
//
//    }
//}
