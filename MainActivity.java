package com.example.android.countrylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_COUNTRY = "countryName";
    public static final String EXTRA_CAPITAL = "capitalName";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;


    public MainActivity(ExampleAdapter mExampleAdapter) {
        this.mExampleAdapter = mExampleAdapter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList  = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }
    private void parseJSON(){
        final String url = "https://restcountries.eu/rest/v2/all";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            private Object ExampleAdapter;

            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = new JSONArray(url);
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                       JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String countryName = jsonObject.getString("name");
                        String imageUrl = jsonObject.getString("flag");
                        String capitalName = jsonObject.getString("capital");

                        mExampleList.add(new ExampleItem(imageUrl,countryName,capitalName));
                    }

                    ExampleAdapter = new ExampleAdapter(MainActivity.this,mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                    mExampleAdapter.setOnItemClickListener(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent  = new Intent(this,DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);
        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_COUNTRY,clickedItem.getmCountry());
        detailIntent.putExtra(EXTRA_CAPITAL,clickedItem.getmCapital());

        startActivity(detailIntent);
    }
}
