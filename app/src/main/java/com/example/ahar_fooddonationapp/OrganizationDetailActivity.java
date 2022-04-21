package com.example.ahar_fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahar_fooddonationapp.Models.Token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrganizationDetailActivity extends AppCompatActivity {

    private TextView email,phone,location,name;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);
        name=findViewById(R.id.orgNameTV);
        email= findViewById(R.id.orgEmailTV);
        phone=findViewById(R.id.orgPhoneTV);
        location=findViewById(R.id.orgLocationTV);


        Bundle bundle= getIntent().getExtras();

        String nameV=bundle.getString("name");
        String emailV=bundle.getString("email");
        String phoneV=bundle.getString("phone");
        String locationV=bundle.getString("location");

        name.setText("name: "+nameV);
        email.setText("email: "+emailV);
        phone.setText("phone: "+phoneV);
        location.setText(locationV);
        btn= findViewById(R.id.reqDonate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReq();
                Toast.makeText(OrganizationDetailActivity.this, "Request has been forwarded" +
                        "Organization will reach you soon",Toast.LENGTH_LONG).show();
            }
        });


    }
    public void sendReq(){
        OkHttpClient client=new OkHttpClient();
        MediaType JSON=MediaType.parse(("application/json;charset=utf-8"));
        JSONObject actualData=new JSONObject();

        try {
            actualData.put("organizationEmail",email.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body= RequestBody.create(JSON,actualData.toString());
        Log.d("OKHTTP","RequestBody Created");
        okhttp3.Request newReq=new Request.Builder()
                .url("http://192.168.0.102:8080/api/UserDonatesOrganization/new-req")
                .header("Authorization",Token.getToken())
                .post(body)
                .build();
        try {
            Response res=client.newCall(newReq).execute();
            String token=" ";
            if(res.isSuccessful()){

            }
            else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    }
