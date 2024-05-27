package com.hemang.time_table_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day extends AppCompatActivity
{
    String URL="http://10.1.51.7:8000/api/lectures/";
    String d ;
    TextView sub1;
    TextView sub2;
    TextView sub3;
    TextView sub4;
    TextView sub5;

    ArrayList<lectureModel> lectureModelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Intent intent = getIntent();

        sub1 = findViewById(R.id.sub1);
        sub2 = findViewById(R.id.sub2);
        sub3 = findViewById(R.id.sub3);
        sub4 = findViewById(R.id.sub4);
        sub5 = findViewById(R.id.sub5);
        d = intent.getStringExtra("day");

        getAPI_JSON();

//        lectureModelArrayList

//        switch (d){
//
//            case "MONDAY":
//                sub1.setText("Data Structure");
//                sub2.setText("Python");
//                sub3.setText("Software Testing");
//                sub4.setText("Data Structure");
//                sub5.setText("Data Structure");
//                break;
//
//            case "TUESDAY":
//                sub1.setText("Software Testing");
//                sub2.setText("Software Testing");
//                sub3.setText("Data Structure");
//                sub4.setText("Mobile Computing");
//                sub5.setText("Mobile Computing");
//                break;
//
//            case "WEDNESDAY":
//                sub1.setText("Python");
//                sub2.setText("Data Structure");
//                sub3.setText("Software Testing");
//                sub4.setText("Mobile Computing");
//                sub5.setText("Mobile Computing");
//                break;
//
//            case "THURSDAY":
//                sub1.setText("Computer Networking");
//                sub2.setText("Software Testing");
//                sub3.setText("Python");
//                sub4.setText("Computer Networking");
//                sub5.setText("Computer Networking");
//                break;
//
//            case "FRIDAY":
//                sub1.setText("Python");
//                sub2.setText("Python");
//                sub3.setText("Computer Networking");
//                sub4.setText("Software Project");
//                sub5.setText("Software Project");
//                break;
//
//            case "SATURDAY":
//                sub1.setText("Python");
//                sub2.setText("Software Testing");
//                sub3.setText("Computer Networking");
//                sub4.setText("Software Project");
//                sub5.setText("Software Project");
//                break;
//        }

    }

    private void getAPI_JSON()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", MODE_PRIVATE);
        String token = sharedPreferences.getString("csrf_token", "0");

        JsonObjectRequest jsonRequest = new JsonObjectRequest(URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        ArrayList<String> staffModelArrayList=new ArrayList<>();

                        try
                        {
//                            Toast.makeText(Staff.this, "Try!!", Toast.LENGTH_SHORT).show();
//                            Log.d("abc", "Try!!");
                            JSONObject jsonArray=response;
//                            Log.d("abc", "1!!");

                            //change name in json Array
                            JSONArray lectureArray = jsonArray.getJSONArray("lecture");
                            JSONArray subjectArray = jsonArray.getJSONArray("subject");
//                            Log.d("abc", "2!!");
                            for (int i = 0; i < lectureArray.length(); i++) {
                                JSONObject lectureObject = lectureArray.getJSONObject(i);
//                                Log.d("abc", "For!!")
                                String day = lectureObject.getString("day");
                                Integer subject = lectureObject.getInt("subject");
                                Integer time_slot = lectureObject.getInt("time_slot");
                                for (int j = 0; j < subjectArray.length(); j++) {
                                    JSONObject subjectObject = subjectArray.getJSONObject(j);
                                    String name = subjectObject.getString("name");
                                    Integer sub_id = subjectObject.getInt("sub_id");

                                    if (day.equals(d))
                                    {
                                        Log.d("abc","day : "+day);
                                        Log.d("abc","time : "+time_slot);
                                        if (time_slot == 1) {
                                            if(subject == sub_id)
                                            {
                                                sub1.setText(name);
                                            }
                                        }
                                        else if (time_slot == 2) {
                                            if(subject == sub_id)
                                            {
                                                sub2.setText(name);
                                            }
                                        }
                                        else if (time_slot == 3) {
                                            if(subject == sub_id)
                                            {
                                                sub3.setText(name);
                                            }
                                        }
                                        else if (time_slot == 5) {
                                            if(subject == sub_id)
                                            {
                                                sub4.setText(name);
                                            }
                                        }
                                        else if (time_slot == 6) {
                                            if(subject == sub_id)
                                            {
                                                sub5.setText(name);
                                            }
                                        }
//                                        lectureModel lec = new lectureModel(day, subject,time_slot);
//                                        lectureModelArrayList.add(lec);
                                    }
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(day.this, "error : "+e, Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("abc", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        )
        {
            @Override
            public Map getHeaders() throws AuthFailureError
            {
                HashMap headers = new HashMap();
                headers.put("Accept", "*/*");
                headers.put("csrf_token", token);
                return headers;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonRequest);
    }

}