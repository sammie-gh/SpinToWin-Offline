package com.learnoset.spintowin;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // sectors of our wheel (look at the image to see the sectors)
    private static final String[] sectors = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    // degree of every sector (Eg. Where the sector is positioned(angled) in image)
    private static final int[] sectorDegrees = new int[sectors.length];

    // create a Random instance to make our wheel spin randomly
    private static final Random RANDOM = new Random();

    private ImageView wheel;

    private int degree = 0;

    private boolean isSpinning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize widgets from activity_main.xml file
        wheel = findViewById(R.id.wheel);
        ImageView spinBtn = findViewById(R.id.spinBtn);

        // get degree of every sector where it positioned in your wheel image
        getDegreeOfEverySector();

        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if wheel is not already spinning
                if (!isSpinning) {
                    spin();
                    isSpinning = true;
                }
            }
        });
    }

    public void spin() {
        // we calculate random angle for rotation of our wheel
        degree = RANDOM.nextInt(sectors.length - 1);

        // rotation effect on the center of the wheel
        RotateAnimation rotateAnim = new RotateAnimation(0, (360 * sectors.length) + sectorDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600); // rotation duration in milliseconds
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(MainActivity.this, "Yo've got " + sectors[sectors.length - (degree + 1)] + " Points", Toast.LENGTH_LONG).show();
                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // we start the animation
        wheel.startAnimation(rotateAnim);
    }

    private void getDegreeOfEverySector() {

        // get one sector degree(angle) among total sectors
        int sectorDegree = 360 / sectors.length;

        for (int i = 0; i < sectors.length; i++) {
            sectorDegrees[i] = (i + 1) * sectorDegree;
        }
    }
}