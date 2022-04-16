package com.example.ahar_fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.ahar_fooddonationapp.Models.HttpsTrustManager;
import com.example.ahar_fooddonationapp.databinding.ActivitySignUpBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding activitySignUpBinding;
    private RequestQueue mQueue;
    private String signUpUrl="http://192.168.0.102:8080/api/authenticate/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //binding to review
        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        //since binding is used
        setContentView(activitySignUpBinding.getRoot());
        // hide the bar
        getSupportActionBar().hide();
        mQueue=Volley.newRequestQueue(this);
        activitySignUpBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SignUpActivity.this, "User Created Successfully, Now you can Sign in", Toast.LENGTH_SHORT).show();
//                activitySignUpBinding.tempText.setText("Fullname: "+activitySignUpBinding.etFullName.getText().toString()+
//                "\nEmail: "+activitySignUpBinding.etEmail.getText().toString()+
//                        "\nPassword: "+activitySignUpBinding.etPassword.getText().toString()+
//                        "\n Phone: "+activitySignUpBinding.etPhone.getText().toString()
//                );
                JsonParse();
            }
        });
        activitySignUpBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void JsonParse(){
        HttpsTrustManager.allowAllSSL();
        StringRequest jsonObjectRequest= new StringRequest(Request.Method.POST, signUpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //parseJson(response);
                    activitySignUpBinding.tempText.setText("Response");
                }
                catch (Exception e){
                    activitySignUpBinding.tempText.setText("Response catch");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                    activitySignUpBinding.tempText.setText("ERROR");
            }
        })
        {
        @Override
        protected Map<String,String> getParams(){
            Map<String, String> params = new HashMap<String, String>();
            params.put("fullName",activitySignUpBinding.etFullName.getText().toString());
            params.put("email",activitySignUpBinding.etEmail.getText().toString());
            params.put("password",activitySignUpBinding.etPassword.getText().toString());
            params.put("phoneNumber",activitySignUpBinding.etPhone.getText().toString());

            return params;
        }
        };
        mQueue.add(jsonObjectRequest);
    };
    private void sendPostRequest(){
        //
        HttpsTrustManager.allowAllSSL();
        /*RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, signUpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUpActivity.this, "CAME HERE - response success",Toast.LENGTH_SHORT).show();
                        activitySignUpBinding.tempText.setText(response.toString());
                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        setIntent(intent);
                        parseJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        activitySignUpBinding.tempText.setText(error.getMessage().toString());
                    }
                }){
            //@Override
            protected Map<String,String> getParmas() {
                Map<String,String>params =new HashMap<String, String>();
                params.put("fullname",activitySignUpBinding.etFullName.getText().toString());
                params.put("email",activitySignUpBinding.etEmail.getText().toString());
                params.put("password",activitySignUpBinding.etPassword.getText().toString());
                params.put("phoneNumber",activitySignUpBinding.etPhone.getText().toString());
                return params;
            };
        };
        queue.add(stringRequest);*/



    }
    private void postDataUsingVolley() {
        HttpsTrustManager.allowAllSSL();
        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, signUpUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                activitySignUpBinding.tempText.setText("RESPONSE");
                //activitySignUpBinding.tempText.setText(response.toString());
                //Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                //setIntent(intent);
                //parseJson(response);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this,"|",Toast.LENGTH_SHORT);

            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("fullname",activitySignUpBinding.etFullName.getText().toString());
                params.put("email",activitySignUpBinding.etEmail.getText().toString());
                params.put("password",activitySignUpBinding.etPassword.getText().toString());
                params.put("phoneNumber",activitySignUpBinding.etPhone.getText().toString());

                return params;
            }
        };
        queue.add(request);
    }
    public void parseJson(String jsonStr){
//        JSONObject jsonObject=null;
//        try {
//            jsonObject=new JSONObject(jsonStr);
//            if (jsonObject.get("Status").equals("Success")){
//                //
//                //Toast.makeText(SignUpActivity.this, "CAME HERE",Toast.LENGTH_SHORT);
//                showAlert(jsonObject.get("message").toString());
//            }else {
//                //showAlert(jsonObject.get("message").toString());
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        activitySignUpBinding.tempText.setText("JSON METHOD");
    }
    public void showAlert(String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert=builder.create();
        alert.show();
    }
}