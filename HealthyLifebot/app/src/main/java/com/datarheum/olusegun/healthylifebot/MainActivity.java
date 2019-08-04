package com.datarheum.olusegun.healthylifebot;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent aftersplash = new Intent(MainActivity.this , Login.class);
                startActivity(aftersplash);
                finish();
            }
        }

        ,SPLASH_TIME_OUT);



    }
}
