package com.example.class5dataparsing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity5 extends AppCompatActivity {

    ProgressBar progressBar;

    RecyclerView recyclerView;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();

    HashMap<String,String>hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        progressBar = findViewById(R.id.progsss);

        recyclerView = findViewById(R.id.listView);






        String url="https://sowrovnil5bd.000webhostapp.com/apps/test1.json";



        progressBar.setVisibility(View.VISIBLE);


        //<<<<<<<<<<<<<<<<<<<   JsonArrayRequest Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        JsonArrayRequest arrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.INVISIBLE);

                Log.d("data",response.toString());

                try {

                    for (int x=0;x<response.length();x++){

                        JSONObject jsonObject=response.getJSONObject(x);

                        String name=jsonObject.getString("name");

                        String tittle=jsonObject.getString("tittle");


                        hashMap=new HashMap<>();
                        hashMap.put("tittle",tittle);
                        hashMap.put("name",name);
                        arrayList.add(hashMap);



                    }

//<<<<<<<<<<<<<<<<<<<<<<<<<<<< Adapter call korte hobe  for loop  ar pore  Start >>>>>>>>>>>>>>>>>

                    Myadapter myadapter=new Myadapter();
                    recyclerView.setAdapter(myadapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity5.this) );

//<<<<<<<<<<<<<<<<<<<<<<<<<<<< Adapter call korte hobe  for loop  ar pore  End >>>>>>>>>>>>>>>>>


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(MainActivity5.this, "dont work ", Toast.LENGTH_SHORT).show();





            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity5.this);

        requestQueue.add(arrayRequest);












    }


    public  class  Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{


        public class  ViewHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            TextView  textView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView=itemView.findViewById(R.id.image1);
                textView=itemView.findViewById(R.id.tittle1);
            }
        }



        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater=getLayoutInflater();

//       <<<<<<<<<<<<<<<<<<<<< layout inflate korar somai  <<<  parent >>> deoa jabe na  Start>>>>>>>>>>>>

            View myview=layoutInflater.inflate(R.layout.item, null);

            //<<<<<<<<<<<<<<<<<<<<< layout inflate korar somai  <<<  parent >>> deoa jabe na END>>>>>>


            return new ViewHolder(myview);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            HashMap<String,String>hashMap=arrayList.get(position);

           String name= hashMap.get("name");

           String tittle=hashMap.get("tittle");

        holder.textView.setText(name);

            String url = "https://img.youtube.com/vi/"+tittle+"/0.jpg";



            Picasso.get().load(url).into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }














    }










































}