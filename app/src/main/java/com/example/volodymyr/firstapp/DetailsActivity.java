package com.example.volodymyr.firstapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ImageView image = findViewById(R.id.image);
        BeerImageDto beer = (BeerImageDto)getIntent().getSerializableExtra("beer");
        Picasso.get().load(beer.getUrl())
//                        .resize(450, 800)
//                .centerInside()
                .into(image);
        ((TextView)findViewById(R.id.name)).setText(beer.getName());
        ((TextView)findViewById(R.id.taglineValue)).setText(beer.getTagline());
        ((TextView)findViewById(R.id.yearValue)).setText(beer.getYear());
        ((TextView)findViewById(R.id.abvValue)).setText(beer.getAbv());
        ((TextView)findViewById(R.id.descValue)).setText(beer.getDescription());

    }
}
