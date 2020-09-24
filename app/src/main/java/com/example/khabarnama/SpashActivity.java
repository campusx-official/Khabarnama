package com.example.khabarnama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SpashActivity extends AppCompatActivity {

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        user = FirebaseAuth.getInstance().getCurrentUser();

        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(user == null){
                    Intent i = new Intent(SpashActivity.this,LoginActivity.class);
                    startActivity(i);

                }else{
                    Intent i = new Intent(SpashActivity.this,MainActivity.class);
                    startActivity(i);

                }

                finish();

            }
        },3000);
    }
}