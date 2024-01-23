package com.example.kinaiya;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimatedVectorDrawable animation; // Declare AnimatedVectorDrawable variable

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.splashView);

        // Load the AnimatedVectorDrawable from the resource
        animation = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.activity_splash, null);

        // Set the AnimatedVectorDrawable on the ImageView
        image.setImageDrawable(animation);

        // Start the animation
        animation.start();
    }


}
