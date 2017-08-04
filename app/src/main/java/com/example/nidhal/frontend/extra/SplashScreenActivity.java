package com.example.nidhal.frontend.extra;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.mainclasses.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    //4 seconds
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent AcceuilIntent = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(AcceuilIntent);
                finish();
            }

        },SPLASH_TIME_OUT);
    }
}
