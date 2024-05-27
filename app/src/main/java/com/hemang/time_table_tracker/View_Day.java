package com.hemang.time_table_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class View_Day extends AppCompatActivity implements View.OnClickListener
{
    private CardView mon,tue,wed,thr,fri,sat;

//    String value = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_day);

        mon = (CardView) findViewById(R.id.cardmon);
        tue = (CardView) findViewById(R.id.cardtue);
        wed = (CardView) findViewById(R.id.cardwed);
        thr = (CardView) findViewById(R.id.cardthr);
        fri = (CardView) findViewById(R.id.cardfri);
        sat = (CardView) findViewById(R.id.cardsat);

        mon.setOnClickListener((View.OnClickListener) this);
        tue.setOnClickListener((View.OnClickListener) this);
        wed.setOnClickListener((View.OnClickListener) this);
        thr.setOnClickListener((View.OnClickListener) this);
        fri.setOnClickListener((View.OnClickListener) this);
        sat.setOnClickListener((View.OnClickListener) this);


    }

    @Override

    public  void onClick(View view)
    {

        Intent i;

        switch (view.getId())
        {
            case R.id.cardmon: i = new Intent(View_Day.this,day.class);
            i.putExtra("day", "MONDAY");
            startActivity(i);
            break;

            case R.id.cardtue: i = new Intent(View_Day.this,day.class);
            i.putExtra("day", "TUESDAY");
            startActivity(i);
            break;

            case R.id.cardwed: i = new Intent(View_Day.this,day.class);
            i.putExtra("day", "WEDNESDAY");
            startActivity(i);
            break;

            case R.id.cardthr: i = new Intent(View_Day.this,day.class);
            i.putExtra("day", "THURSDAY");
            startActivity(i);
            break;

            case R.id.cardfri: i = new Intent(View_Day.this,day.class);
            i.putExtra("day", "FRIDAY");
            startActivity(i);
            break;

            case  R.id.cardsat: i = new Intent(View_Day.this,day.class);
            i.putExtra("day", "SATURDAY");
            startActivity(i);
            break;
        }

    }

    }
