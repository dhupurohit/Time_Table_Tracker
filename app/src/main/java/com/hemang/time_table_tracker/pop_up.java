package com.hemang.time_table_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class pop_up extends AppCompatActivity
{
//    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up1);

//        myDialog = new Dialog(this);
    }

//    public void show_dialog(View v)
//    {
//        TextView close_btn;
//
//        myDialog.setContentView(R.layout.activity_pop_up);
//        close_btn = (TextView) myDialog.findViewById(R.id.close_btn);
//        close_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
}