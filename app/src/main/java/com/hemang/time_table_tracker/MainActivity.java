package com.hemang.time_table_tracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
//        String URL = "http://localhost/LogInAPI/user/login_check.php";
//    String URL = "http://192.168.0.201:8000/api/login/";
    String URL = "http://10.1.51.7:8000/api/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ediEmail = findViewById(R.id.ediemail);
        EditText ediPassword = findViewById(R.id.edipas);
        Button btnLogin = findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ediEmail.getText().toString().trim().isEmpty()){
                    ediEmail.setError("Enter username");
                } else if(ediPassword.getText().toString().trim().isEmpty()){
                    ediPassword.setError("Enter Password");
                } else {
//                    loginCall(ediEmail.getText().toString().trim(),ediPassword.getText().toString().trim());
                    loginCall_JSON(ediEmail.getText().toString().trim(),ediPassword.getText().toString().trim());
                }
            }
        });

    }

    private void loginCall(String ediEmail,String ediPassword) {
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                        String userInfo;
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            userInfo=jsonArray.get(0).toString();

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        // Save user to Shared Preference
//                        SharedPreferencesUtils.saveString(getApplicationContext(), "userInfo", userInfo);
                        Intent i = new Intent(getApplicationContext(),Admin.class);
                        i.putExtra("userInfo",userInfo);
                        startActivity(i);
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("abc", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map= new HashMap<>();
                map.put("email",ediEmail);
                map.put("password",ediPassword);
                //return super.getParams();
                return  map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void loginCall_JSON(String ediEmail,String ediPassword) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", ediEmail);
        params.put("password", ediPassword);
        JSONObject jsonObject=new JSONObject(params);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String csrf_token  = response.getString("csrf_token");

                            SharedPreferences sharedPreferences = getSharedPreferences("tokenPref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("csrf_token",csrf_token);
                            editor.apply();

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),Admin.class);
                        startActivity(i);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("abc", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        );
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }





}