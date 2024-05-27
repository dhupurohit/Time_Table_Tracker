package com.hemang.time_table_tracker;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity
{

    Dialog myDialog1,myDialog2,myDialog3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        myDialog1 = new Dialog(this);
        myDialog2 = new Dialog(this);
        myDialog3 = new Dialog(this);

    }

    public void show_dialog1(View v)
    {
        TextView close_btn1;

        myDialog1.setContentView(R.layout.activity_pop_up1);
        close_btn1 = (TextView) myDialog1.findViewById(R.id.close_btn1);
        close_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog1.dismiss();
            }
        });
        myDialog1.show();
    }

    public void show_dialog2(View v)
    {
        TextView close_btn2;

        myDialog2.setContentView(R.layout.activity_pop_up2);
        close_btn2 = (TextView) myDialog2.findViewById(R.id.close_btn2);
        close_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog2.dismiss();
            }
        });
        myDialog2.show();
    }

    public void show_dialog3(View v)
    {
        TextView close_btn3;

        myDialog3.setContentView(R.layout.activity_pop_up3);
        close_btn3 = (TextView) myDialog3.findViewById(R.id.close_btn3);
        close_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog3.dismiss();
            }
        });
        myDialog3.show();
    }
}