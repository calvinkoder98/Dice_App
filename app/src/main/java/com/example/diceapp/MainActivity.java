package com.example.diceapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button rollButton = findViewById(R.id.rollButton);
        /*final TextView dice1Result = findViewById(R.id.dice1Result);
        final TextView dice2Result = findViewById(R.id.dice2Result);*/
        final TextView total = findViewById(R.id.total);
        final TextView totalText = findViewById(R.id.totalText);
        final ImageView dice1Image = findViewById(R.id.dice1Image);
        final ImageView dice2Image = findViewById(R.id.dice2Image);


        rollButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                totalText.setText("The total is:");

                final Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                final Animation animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);

                Log.d(TAG, "booya");
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d(TAG, "check");
                        int dice1 = ThreadLocalRandom.current().nextInt(1,6+1);
                        int dice2 = ThreadLocalRandom.current().nextInt(1,6+1);
                        int addedTotal = dice1 + dice2;

                        total.setText(new StringBuilder().append(addedTotal));

                        int dice1Resource = getResources().getIdentifier("dice" + dice1, "drawable", "com.example.diceapp");
                        int dice2Resource = getResources().getIdentifier("dice" + dice2, "drawable", "com.example.diceapp");

                        dice1Image.setImageResource(dice1Resource);
                        dice2Image.setImageResource(dice2Resource);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };

                animation1.setAnimationListener(animationListener);
                animation2.setAnimationListener(animationListener);

                dice1Image.startAnimation(animation1);
                dice2Image.startAnimation(animation2);


            }

        });

    }
}
