package com.hemang.time_table_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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

public class Subject extends AppCompatActivity
{

    String URL="http://10.1.51.7:8000/api/courseSubject/";

    ListView sublist;


    Integer id;

//    String URL="http://192.168.0.103:8000/api/courseSubject/";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        if(i.getIntExtra("courseId",-1) != -1)
        {
            id=i.getIntExtra("courseId",-1);
            URL += id.toString();
        }
        Log.d("abc", "courseId"+i.getIntExtra("courseId",-1));
        getAPI_JSON();
        setContentView(R.layout.activity_subject);

        sublist = findViewById(R.id.sublist);
    }

    private void getAPI_JSON()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", MODE_PRIVATE);
        String token = sharedPreferences.getString("csrf_token", "0");

//        Toast.makeText(Staff.this, "!!"+token , Toast.LENGTH_SHORT).show();
//        Log.d("abc", token);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(URL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        ArrayList<String> staffModelArrayList=new ArrayList<>();
                        ArrayList<subModel> subModelArrayList=new ArrayList<>();
                        try
                        {
//                            Toast.makeText(Staff.this, "Try!!", Toast.LENGTH_SHORT).show();
//                            Log.d("abc", "Try!!");
                            JSONObject jsonArray=response;
//                            Log.d("abc", "1!!");

                            //change name in json Array
                            JSONArray subArray = jsonArray.getJSONArray("subject");
//                            Log.d("abc", "2!!");
                            for (int i = 0; i < subArray.length(); i++) {
                                JSONObject subObject  = subArray.getJSONObject(i);
//                                Log.d("abc", "For!!");
                                String name = subObject.getString("name");
                                subModel stm = new subModel(name);
                                subModelArrayList.add(
                                        stm
//                                        username
                                );
                            }
                            subAdapter  subAdapter=new subAdapter(
                                    Subject.this,
                                    R.layout.cource_list,
                                    subModelArrayList
                            );
                            sublist.setAdapter(subAdapter);
//                            Toast.makeText(Subject.this, "Cource!!", Toast.LENGTH_SHORT).show();

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(Subject.this, "error : "+e, Toast.LENGTH_SHORT).show();
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