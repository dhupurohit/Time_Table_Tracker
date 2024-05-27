package com.hemang.time_table_tracker;

//import static com.hemang.time_table_tracker.staffModel.name;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Staff extends AppCompatActivity
{

    //    String URL="https://api.github.com/users";
//    String URL="http://192.168.0.201:8000/api/staffList/";
//    String URL="http://192.168.0.103:8000/api/staffList/";
    String URL="http://10.1.51.7:8000/api/staffList/";

    ListView staffList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getAPI_JSON();
        setContentView(R.layout.activity_staff);
        staffList = findViewById(R.id.staffList);
//        final int lastClickedPosition = -1;


//        staffList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (lastClickedPosition != -1 && lastClickedPosition != position) {
//                    View lastClickedView = staffList.getChildAt(lastClickedPosition - staffList.getFirstVisiblePosition());
//                    // Close the previously opened item
//                    // Implement closing logic here
//                }
//
//                if (lastClickedPosition == position) {
//                    // Close the currently opened item
//                    // Implement closing logic here
//                    lastClickedPosition = -1;
//                } else {
//                    // Open the clicked item and display name and email
//                    // Implement opening logic here
//                    lastClickedPosition = position;
//                }
//            }
//        });

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
                        ArrayList<staffModel> staffModelArrayList=new ArrayList<>();
                        try {
//                            Toast.makeText(Staff.this, "Try!!", Toast.LENGTH_SHORT).show();
//                            Log.d("abc", "Try!!");
                            JSONObject jsonArray=response;
//                            Log.d("abc", "1!!");
                            JSONArray staffArray = jsonArray.getJSONArray("staff");
//                            Log.d("abc", "2!!");
                            for (int i = 0; i < staffArray.length(); i++) {
                                JSONObject staffObject = staffArray.getJSONObject(i);
//                                Log.d("abc", "For!!");
                                int id = staffObject.getInt("id");
                                String username = staffObject.getString("username");
                                String email = staffObject.getString("email");
                                String firstName = staffObject.getString("first_name");
                                String lastName = staffObject.getString("last_name");
                                staffModel stm = new staffModel(id,username,email,firstName,lastName);
                                staffModelArrayList.add(
                                        stm
//                                        username
                                );
                            }
                            staffAdapter  staffAdapter=new staffAdapter(
                                    Staff.this,
                                    R.layout.staff_list,
                                    staffModelArrayList
                            );
                            staffList.setAdapter(staffAdapter);
                            Toast.makeText(Staff.this, "Staff!!", Toast.LENGTH_SHORT).show();

                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(Staff.this, "error : "+e, Toast.LENGTH_SHORT).show();
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
            public Map getHeaders() throws AuthFailureError {
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
