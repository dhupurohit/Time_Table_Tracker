package com.hemang.time_table_tracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin extends AppCompatActivity implements View.OnClickListener
{

    private CardView staff,lectures,student,cource,about,logout;

    Dialog dialog;
    Button btn_no,btn_yes;
//    Button btn;
//    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        staff = (CardView) findViewById(R.id.cardstaff);
        lectures = (CardView) findViewById(R.id.cardlec);
        student = (CardView) findViewById(R.id.cardstud);
        cource = (CardView) findViewById(R.id.cardcource);
        logout = (CardView) findViewById(R.id.cardlogout);
        about = (CardView) findViewById(R.id.cardabout);
        dialog = new Dialog(Admin.this);





        staff.setOnClickListener((View.OnClickListener)this);
        lectures.setOnClickListener((View.OnClickListener)this);
        student.setOnClickListener((View.OnClickListener)this);
        cource.setOnClickListener((View.OnClickListener)this);
        logout.setOnClickListener((View.OnClickListener)this);
        about.setOnClickListener((View.OnClickListener)this);

    }

    @Override

    public void onClick(View v)
    {
        Intent i;

        switch (v.getId())
        {
            case R.id.cardstaff: i = new Intent(Admin.this,Staff.class);
            startActivity(i);
            break;

            case R.id.cardlec: i = new Intent(Admin.this,View_Day.class);
            startActivity(i);
            break;

            case R.id.cardstud: i = new Intent(Admin.this,Student.class);
            startActivity(i);
            break;

            case R.id.cardcource: i = new Intent(Admin.this,Course.class);
            startActivity(i);
            break;

            case R.id.cardlogout:

                SimpleAlert();
//                i = new Intent(Admin.this,MainActivity.class);
//            startActivity(i);
//            finish();
            break;

            case R.id.cardabout: i = new Intent(Admin.this,About.class);
            startActivity(i);
            break;
        }

    }
    void SimpleAlert()
    {
        AlertDialog.Builder ad=new AlertDialog.Builder(Admin.this);
        ad.setTitle("Logout");
        ad.setMessage("Are you sure you want to Log out?");
        ad.setCancelable(false);


        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent i;
                i = new Intent(Admin.this,MainActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("csrf_token","0");
                editor.apply();
                startActivity(i);
                finish();
            }
        });

        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });


        ad.create();
        ad.show();
    }
   



    


}