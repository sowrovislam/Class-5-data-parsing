package com.example.class5dataparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity4 extends AppCompatActivity {

    TextView text1,text2;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        text1=findViewById(R.id.text1);

        progressBar=findViewById(R.id.progss);


        String url="https://sowrovnil5bd.000webhostapp.com/apps/test1.json";



           progressBar.setVisibility(View.VISIBLE);


           //<<<<<<<<<<<<<<<<<<<   JsonArrayRequest Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        JsonArrayRequest arrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.INVISIBLE);

                Log.d("data",response.toString());

                try {


                    //<<<<<<<<<<<<<<<<<<<    for loop diye   array list hote  sob ghulo  data ber korte hoi  array  list ar
                    //  vitore sob  fanshon  ar kaj  korte hobe >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



                    for (int x=0;x<response.length();x++){


                        //<<<<<<<<<<<<<<<<<<<   JsonArrayRequest   ar vitore JsonObajct ke colla kora jai  Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


                        JSONObject jsonObject=response.getJSONObject(x);


                        //<<<<<<<<<<<<<<<<<<<   JsonArrayRequest   ar vitore JsonObajct ke colla kora jai  End>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


                        String name=jsonObject.getString("name");

                        String tittle=jsonObject.getString("tittle");



                        text1.append(x+"."+name+"\n"+tittle+"\n \n");


                    }







                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                text1.setText("dont work");



            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity4.this);

        requestQueue.add(arrayRequest);




    }
}