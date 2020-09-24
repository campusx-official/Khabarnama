package com.example.khabarnama;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AppData {

    ArrayList<String> news_headline,news_description,news_poster,news_source,news_url;
    int numResponse;

    AppData(){
        news_headline = new ArrayList<String>();
        news_description = new ArrayList<String>();
        news_poster = new ArrayList<String>();
        news_source = new ArrayList<String>();
        news_url = new ArrayList<String>();
    }

    public void fetchFromFirebase(DataSnapshot dataSnapshot, Context context, RecyclerView recyclerView){

        for(DataSnapshot thisSnapShot : dataSnapshot.getChildren()){
            news_headline.add(thisSnapShot.child("headline").getValue().toString());
            news_description.add(thisSnapShot.child("description").getValue().toString());
            news_poster.add(thisSnapShot.child("poster").getValue().toString());
            news_url.add(thisSnapShot.child("url").getValue().toString());

            news_source.add("Unknown source");
        }

        CustomAdapter customAdapter = new CustomAdapter(context,news_headline,news_description,news_poster,news_source,news_url);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(customAdapter);

    }

    public int fetch(String url, final RecyclerView recyclerView, final Context context){

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject parent = new JSONObject(response);
                            JSONArray articles = parent.getJSONArray("articles");

                            for(int i=0; i<articles.length();i++){
                                JSONObject current = articles.getJSONObject(i);
                                news_headline.add(current.getString("title"));
                                news_description.add(current.getString("description"));
                                news_poster.add(current.getString("urlToImage"));
                                JSONObject sourceObject = current.getJSONObject("source");
                                news_source.add(sourceObject.getString("name"));
                                news_url.add(current.getString("url"));
                            }

                            CustomAdapter customAdapter = new CustomAdapter(context,news_headline,news_description,news_poster,news_source,news_url);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(customAdapter);

                            numResponse = news_headline.size();


                        } catch (JSONException e) {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        return numResponse;

    }
}
