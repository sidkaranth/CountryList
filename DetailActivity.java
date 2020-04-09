package com.example.android.countrylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.android.countrylist.MainActivity.EXTRA_CAPITAL;
import static com.example.android.countrylist.MainActivity.EXTRA_COUNTRY;
import static com.example.android.countrylist.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String countryName = intent.getStringExtra(EXTRA_COUNTRY);
        String capitalName = intent.getStringExtra(EXTRA_CAPITAL);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCountry = findViewById(R.id.text_view_country_detail);
        TextView textViewCapital = findViewById(R.id.text_view_capital_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewCapital.setText(countryName);
        textViewCapital.setText(capitalName);


    }
}
