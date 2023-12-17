
package com.example.class5dataparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.lang.reflect.Method;

public class MainActivity2 extends AppCompatActivity {

    ProgressBar progressBar;

    AdView mAdView;


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progressBar = findViewById(R.id.prog);

        textView = findViewById(R.id.textView);

        progressBar.setVisibility(View.VISIBLE);
        mAdView = findViewById(R.id.adView);


        //<<<<<<<<<<<<<<<<<<<<<<< Ad Mob  add  Start >>>>>>>>>>>>>>>>>>>>>>>


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        //<<<<<<<<<<<<<<<<<<<<<<< Ad Mob  add  end  >>>>>>>>>>>>>>>>>>>>>>>




        //<<<<<<<<<<<<<<<<<<<<<<<    StringRequest php  start  >>>>>>>>>>>>>>>>>>>>>>>


        String url ="https://sowrovnil5bd.000webhostapp.com/apps/test.php";


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);
                Log.d("data",response);




                if (response.contains("sum")){

                    //<<<<<<<<<<<<<<<<<<<<<<<    StringRequest  ad mob AdRequest  start >>>>>>>>>>>>>>>>>>>>>>>
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);

                    //<<<<<<<<<<<<<<<<<<<<<<<    StringRequest  ad mob AdRequest end    >>>>>>>>>>>>>>>>>>>>>>>



                    textView.setText(response);

                }







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                textView.setText(" not work");




            }
        });



        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity2.this);

        requestQueue.add(stringRequest);

        //<<<<<<<<<<<<<<<<<<<<<<<    StringRequest php  end  >>>>>>>>>>>>>>>>>>>>>>>










    }
}