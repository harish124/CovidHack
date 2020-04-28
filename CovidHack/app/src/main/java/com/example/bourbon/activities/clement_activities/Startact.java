package com.example.bourbon.activities.clement_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bourbon.R;

public class Startact extends AppCompatActivity {

    EditText mobile ;
    Button submit;
    private static final int RC_SIGN_IN = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startact);

//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        if (auth.getCurrentUser() != null) {
//            // already signed in
//        } else {
//            // not signed in
//            startActivityForResult(
//                    AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .setAvailableProviders(Arrays.asList(
//                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
//                                    new AuthUI.IdpConfig.EmailBuilder().build(),
//                                    new AuthUI.IdpConfig.PhoneBuilder().build(),
//                                    new AuthUI.IdpConfig.AnonymousBuilder().build()))
//                            .build(),
//                    RC_SIGN_IN);
//        }



        mobile = findViewById(R.id.mobi);
        submit = findViewById(R.id.sub);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Startact.this, Verifi.class);
                i.putExtra("Mobile",mobile.getText().toString());
                startActivity(i);
            }
        });
    }

}
