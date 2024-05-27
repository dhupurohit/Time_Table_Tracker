package com.hemang.time_table_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class Course extends AppCompatActivity
{
//    String URL="http://192.168.0.103:8000/api/courseList/";
    String URL="http://10.1.51.7:8000/api/courseList/";

    ListView courselist;
    Integer cId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getAPI_JSON();
        setContentView(R.layout.activity_course);

        courselist = findViewById(R.id.courselist);


        courselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent course_i = new Intent(Course.this,Subject.class);
                switch (i){
                    case 0 : course_i.putExtra("courseId", 1);
                    break;
                    case 1 : course_i.putExtra("courseId", 2);
                    break;
                    case 2 : course_i.putExtra("courseId", 5);
                    break;
                    case 3 : course_i.putExtra("courseId", 6);
                    break;
                    default: course_i.putExtra("courseId", -1);
                    break;
                }
                startActivity(course_i);

            }
        });
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
                        ArrayList<courceModel> courceModelArrayList=new ArrayList<>();
                        try
                        {
//                            Toast.makeText(Staff.this, "Try!!", Toast.LENGTH_SHORT).show();
//                            Log.d("abc", "Try!!");
                            JSONObject jsonArray=response;
//                            Log.d("abc", "1!!");

                            //change name in json Array
                            JSONArray courseArray = jsonArray.getJSONArray("course");
//                            Log.d("abc", "2!!");
                            for (int i = 0; i < courseArray.length(); i++) {
                                JSONObject courceObject  = courseArray.getJSONObject(i);
//                                Log.d("abc", "For!!");
                                cId = courceObject.getInt("c_id");
                                String name = courceObject.getString("name");
                                String department = courceObject.getString("department");
                                String semester = courceObject.getString("semester");
                                courceModel stm = new courceModel(name,department,semester);
                                courceModelArrayList.add(
                                        stm
//                                        username
                                );
                            }
                            courceAdapter  courceAdapter=new courceAdapter(
                                    Course.this,
                                    R.layout.cource_list,
                                    courceModelArrayList
                            );
                            courselist.setAdapter(courceAdapter);
                            Toast.makeText(Course.this, "Cource!!", Toast.LENGTH_SHORT).show();

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(Course.this, "error : "+e, Toast.LENGTH_SHORT).show();
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