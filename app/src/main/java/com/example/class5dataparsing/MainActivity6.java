package com.example.class5dataparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity6 extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        textView=findViewById(R.id.text);

        progressBar=findViewById(R.id.ppp);


        String url = "https://sowrovnil5bd.000webhostapp.com/apps/complx.json";

        progressBar.setVisibility(View.VISIBLE);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);


                try {
                    String name= response.getString("name");

                    String age=response.getString("age");



                    JSONArray jsonArray =response.getJSONArray("Video");


                    for (int x=0; x<response.length();x++){



                        JSONObject jsonObject=jsonArray.getJSONObject(x);



                        String video_url=jsonObject.getString("video_url");

                        String tittle=jsonObject.getString("tittle");

                        textView.append(video_url+"\n\n"+tittle+"\n\n");





                    }



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {




            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity6.this);

        requestQueue.add(jsonObjectRequest);












    }
}