package com.example.kinaiya;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private boolean isButtonClicked = false;
    private Toolbar toolbar;
    private ImageView imageView;

    private float originalScaleX;
    private float originalScaleY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);

        // Save the original scale
        originalScaleX = imageView.getScaleX();
        originalScaleY = imageView.getScaleY();

        setButtonClickListeners();
    }

    private void setButtonClickListeners() {
        int[] buttonIds = {
                R.id.imageButton1,
                R.id.imageButton2
                // ... add more button IDs
        };



        for (int buttonId : buttonIds) {
            ImageButton button = findViewById(buttonId);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //hide all buttons
                    for (int buttonId : buttonIds) {
                        View button = findViewById(buttonId);
                        if (button != null) {
                            button.setVisibility(View.GONE);
                        }
                    }
                    handleButtonClick((ImageButton) v);
                }
            });
        }
    }

    private void handleButtonClick(ImageButton button) {
        if (!isButtonClicked) {
            // Hide the TextViews
            findViewById(R.id.textView2).setVisibility(View.GONE);
            findViewById(R.id.textView).setVisibility(View.GONE);

            // Hide the Toolbar and ImageButton
            toolbar.setVisibility(View.GONE);


            // Zoom in animation for the imageView
            zoomInImage(button);

            isButtonClicked = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (isButtonClicked) {
            // Zoom out animation for the imageView
            zoomOutImage(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // Show the TextViews
                    findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                    findViewById(R.id.textView).setVisibility(View.VISIBLE);

                    // Show the Toolbar and all ImageButtons
                    toolbar.setVisibility(View.VISIBLE);
                    showAllButtons();

                    // Reset the flag after the animation is complete
                    isButtonClicked = false;
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    private void zoomOutImage(Animator.AnimatorListener listener) {
        // Set up the ObjectAnimator for the scale animation
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f);

        // Set the duration of the animation
        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);

        // Set up the AnimatorSet to play both animations together
        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        // Set the listener for the end of the animation
        scaleDown.addListener(listener);

        // Start the animation
        scaleDown.start();
    }
    private void zoomInImage(ImageButton button) {
        // Get the coordinates of the ImageButton
        int[] buttonLocation = new int[2];
        button.getLocationOnScreen(buttonLocation);

        // Calculate the center coordinates of the button
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        float centerX = buttonLocation[0] + buttonWidth / 2.0f;
        float centerY = buttonLocation[1] + buttonHeight / 2.0f;

        // Calculate the pivot point relative to the ImageView
        int[] imageViewLocation = new int[2];
        imageView.getLocationOnScreen(imageViewLocation);
        float pivotX = centerX - imageViewLocation[0];
        float pivotY = centerY - imageViewLocation[1];

        // Set the pivot point for the zoom animation
        imageView.setPivotX(pivotX);
        imageView.setPivotY(pivotY);

        // Set up the ObjectAnimator for the scale animation
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(imageView, "scaleX", 4.0f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(imageView, "scaleY", 4.0f);

        // Set the duration of the animation
        scaleUpX.setDuration(1000);
        scaleUpY.setDuration(1000);

        // Start the animation
        scaleUpX.start();
        scaleUpY.start();
    }


    private void showAllButtons() {
        int[] buttonIds = {
                R.id.imageButton1,
                R.id.imageButton2
                // ... add more button IDs
        };

        for (int buttonId : buttonIds) {
            findViewById(buttonId).setVisibility(View.VISIBLE);
        }
    }
}
