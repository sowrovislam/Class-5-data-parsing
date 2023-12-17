package com.example.class5dataparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {


    EditText name1,email1,mobile1;
    AppCompatButton button;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        name1=findViewById(R.id.name1);
        mobile1=findViewById(R.id.mobile1);
        email1=findViewById(R.id.email1);
        button=findViewById(R.id.click);
        progressBar=findViewById(R.id.progs);
        progressBar.setVisibility(View.VISIBLE);



//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< JSON recost  Json Objact >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        String url ="https://sowrovnil5bd.000webhostapp.com/apps/test.json";



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.INVISIBLE);

                        Log.d("sss",response);




                        try {



                            //<<<<<<<<<< inpordent sintax json objact      Start >>>>>>>>>>>>>>>>>>>>>>


                                    JSONObject jsonObject=new JSONObject(response);



                            //<<<<<<<<<< inpordent sintax json objact      End  >>>>>>>>>>>>>>>>>>>>>>

                            String name = jsonObject.getString("name");
                            String mobile =jsonObject.getString("mobile");
                            String email= jsonObject.getString("email");


                            name1.setText(name);
                            mobile1.setText(mobile);
                            email1.setText(email);


                        } catch (JSONException e) {
                            throw new RuntimeException(e);


                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

                RequestQueue requestQueue= Volley.newRequestQueue(MainActivity3.this);
                requestQueue.add(stringRequest);









            }
        });


























    }
}