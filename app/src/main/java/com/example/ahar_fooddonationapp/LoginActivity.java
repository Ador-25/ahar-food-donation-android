package com.example.ahar_fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahar_fooddonationapp.Models.Token;
import com.example.ahar_fooddonationapp.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView signUpText;
    private ProgressDialog progressDialog;
    private final String loginUrl="https://localhost:44347/api/Authenticate/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder()
                .permitAll()
                .build();

        StrictMode.setThreadPolicy(policy);
        username= findViewById(R.id.etUsername);
        password= findViewById(R.id.etPassword);
        loginButton= findViewById(R.id.btn_signIn);
        signUpText = findViewById(R.id.tvClickSignUp);
        progressDialog = new ProgressDialog(LoginActivity.this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLoginRequest();
                progressDialog.show();
                if(Token.getToken().length()>1){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                }

            }

        });


        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendLoginRequest(){

        OkHttpClient client=new OkHttpClient();
        MediaType JSON=MediaType.parse(("application/json;charset=utf-8"));
        JSONObject actualData=new JSONObject();

        try {
            actualData.put("username",username.getText().toString());
            actualData.put("password",password.getText().toString());
        } catch (JSONException e) {
            Log.d("HTTP","Jsaon expection");
            e.printStackTrace();
        }
        RequestBody body= RequestBody.create(JSON,actualData.toString());
        Log.d("OKHTTP","RequestBody Created");
        okhttp3.Request newReq=new Request.Builder()
                .url("http://192.168.0.102:8080/api/authenticate/login")
                .post(body)
                .build();
        try {
            Response res=client.newCall(newReq).execute();
            String token=" ";
            if(res.isSuccessful()){
                try{
                    JSONObject js= new JSONObject(res.body().string());
                    token=js.get("token").toString();
                    Token.setToken(token);
                }
                catch (Exception e){

                }
            }
            else {
                Toast.makeText(LoginActivity.this,
                        "Could not sign in",
                        Toast.LENGTH_SHORT)
                        .show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}