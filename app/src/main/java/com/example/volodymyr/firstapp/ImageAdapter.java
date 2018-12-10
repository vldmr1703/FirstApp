package com.example.volodymyr.firstapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<BeerImageDto> beers;

    public ImageAdapter(Context c, List<BeerImageDto> b) {
        mContext = c;
        beers = b;
    }

    public int getCount() {
        return beers.size();
    }

    public Object getItem(int position) {
        return beers.get(position);
    }

    public long getItemId(int position) {
        return beers.get(position).getId();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CardView cardView;
        ImageView imageView;
        TextView header;
        TextView description;
        TextView abv;
//        if (convertView == null) {
            //create card
            cardView = new CardView(mContext);
            cardView.setRadius(100);
            cardView.setMinimumWidth(800);
//        }
//        else {
//            cardView = (CardView)convertView;
//        }
        //header
        header = new TextView(mContext);
        header.setId(View.generateViewId());
        header.setText(beers.get(position).getName());
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        header.setTextSize(30);
        header.setPadding(20, 10, 20, 10);
        header.setBackgroundResource(R.drawable.gradient);
        header.setTextColor(Color.WHITE);

        //image
        imageView = new ImageView(mContext);
        imageView.setId(View.generateViewId());
        Picasso.get().load(beers.get(position).getUrl())
                .resize(450, 800)
                .centerInside().into(imageView);

        //image layout params
        RelativeLayout.LayoutParams paramsImage = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsImage.addRule(RelativeLayout.BELOW, header.getId());
        paramsImage.addRule(RelativeLayout.CENTER_HORIZONTAL, header.getId());
        imageView.setLayoutParams(paramsImage);
        paramsImage.setMargins(10, 30, 10, 20);

        //header layout params
        RelativeLayout.LayoutParams paramsHeader = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        header.setLayoutParams(paramsHeader);

        RelativeLayout.LayoutParams paramsDesc = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsDesc.setMargins(30, 30, 30, 20);

        RelativeLayout.LayoutParams paramsAbv = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        paramsAbv.setMargins(0, 30, 0, 20);

        //description
        description = new TextView(mContext);
        description.setId(View.generateViewId());
        description.setLayoutParams(paramsImage);
        String text = beers.get(position).getDescription();
        if (text.length() > 300) {
            text = text.substring(0, 300);
        }
        description.setText(text + "..");
        paramsDesc.addRule(RelativeLayout.BELOW, imageView.getId());
        paramsDesc.addRule(RelativeLayout.CENTER_HORIZONTAL, imageView.getId());
        description.setLayoutParams(paramsDesc);
        description.setTextSize(16);
        description.setTypeface(ResourcesCompat.getFont(mContext, R.font.indieflower));

        //abv
        abv = new TextView(mContext);
        abv.setLayoutParams(paramsImage);
        abv.setText("Alcohol by volume: " + beers.get(position).getAbv() + " %");
        abv.setTypeface(Typeface.DEFAULT_BOLD);
        paramsAbv.addRule(RelativeLayout.BELOW, description.getId());
        paramsAbv.addRule(RelativeLayout.CENTER_HORIZONTAL, description.getId());
        abv.setLayoutParams(paramsAbv);


        RelativeLayout layout = new RelativeLayout(mContext);

        //add views to card
        layout.addView(header);
        layout.addView(imageView);
        layout.addView(description);
        layout.addView(abv);
        cardView.addView(layout);
        cardView.setId(beers.get(position).getId());
        return cardView;
    }
}
