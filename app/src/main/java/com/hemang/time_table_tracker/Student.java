package com.hemang.time_table_tracker;

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

public class Student extends AppCompatActivity
{

    String URL="http://10.1.51.7:8000/api/studentList/";
//    String URL="http://192.168.0.103:8000/api/studentList/";

    ListView stdList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getAPI_JSON();
        setContentView(R.layout.activity_student);
        stdList = findViewById(R.id.stdList);
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
                        ArrayList<studentModel> studentModelArrayList=new ArrayList<>();
                        try {
//                            Toast.makeText(Staff.this, "Try!!", Toast.LENGTH_SHORT).show();
//                            Log.d("abc", "Try!!");
                            JSONObject jsonArray=response;
//                            Log.d("abc", "1!!");
                            JSONArray studentArray = jsonArray.getJSONArray("student");
//                            Log.d("abc", "2!!");
                            for (int i = 0; i < studentArray.length(); i++) {
                                JSONObject studentObject = studentArray.getJSONObject(i);
//                                Log.d("abc", "For!!");
                                String name = studentObject.getString("name");
                                int Roll_No = studentObject.getInt("roll_number");
                                String Course = studentObject.getString("course");
                                studentModel stm = new studentModel(name,Roll_No,Course);
                                studentModelArrayList.add(
                                        stm
//                                        username
                                );
                            }
                            studentAdapter  studentAdapter=new studentAdapter(
                                    Student.this,
                                    R.layout.student_list,
                                    studentModelArrayList
                            );
                            stdList.setAdapter(studentAdapter);
                            Toast.makeText(Student.this, "Staff!!", Toast.LENGTH_SHORT).show();

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(Student.this, "error : "+e, Toast.LENGTH_SHORT).show();
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