package com.example.retrofitbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
private final String TAG= "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("intent_title");
        String image = getIntent().getStringExtra("intent_image");
        getSupportActionBar().setTitle(title);
        Picasso.get()
                .load(image)
                .into((ImageView) findViewById(R.id.imageView));
    }
}