package com.example.volodymyr.firstapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    List<BeerImageDto> beerImageDtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beerImageDtos = new ArrayList<>();
        getImages();
    }

    public void getImages() {
        String url = "https://api.punkapi.com/v2/beers";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray arr = new JSONArray(response.toString());
                            for (int i = 0; i < arr.length(); i++) {
                                String id = arr.getJSONObject(i).getString("id");
                                String name = arr.getJSONObject(i).getString("name");
                                String url = arr.getJSONObject(i).getString("image_url");
                                String tagline = arr.getJSONObject(i).getString("tagline");
                                String year = arr.getJSONObject(i).getString("first_brewed");
                                String description = arr.getJSONObject(i).getString("description");
                                String abv = arr.getJSONObject(i).getString("abv");
                                beerImageDtos.add(new BeerImageDto(Integer.valueOf(id), name, url, tagline, year, description, abv));
                            }
                            displayImages();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());

                    }
                });

        VolleyApp.getInstance().getRequestQueue().add(jsonObjectRequest);
    }

    public void displayImages() {
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(MainActivity.this, beerImageDtos));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("beer", beerImageDtos.get(position));
                startActivity(intent);
            }
        });
    }
}
