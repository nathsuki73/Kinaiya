package com.example.kinaiya;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    AnimatedVectorDrawable animation; // Declare AnimatedVectorDrawable variable

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView image = findViewById(R.id.splashView);

        // Load the AnimatedVectorDrawable from the resource
        animation = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.activity_splash, null);

        // Set the AnimatedVectorDrawable on the ImageView
        image.setImageDrawable(animation);

        // Start the animation
        animation.start();

        // Use a handler to delay the transition to the main activity
        new android.os.Handler().postDelayed(
                () -> {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                },
                1000 // Delay for 1 seconds (adjust as needed)
        );


    }
}
