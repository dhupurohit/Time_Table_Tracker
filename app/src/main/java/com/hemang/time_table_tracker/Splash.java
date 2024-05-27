package com.hemang.time_table_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", MODE_PRIVATE);
                String token = sharedPreferences.getString("csrf_token", "0");
                if (token.equals("0")) {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(Splash.this, Admin.class);
                    startActivity(i);
                    finish();

                }
            }
        },4000);

    }
}