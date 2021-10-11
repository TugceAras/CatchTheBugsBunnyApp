package com.tugcearas.catchthebugsbunny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView timeText;
    TextView scoreText;
    int score ;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        score = 0;

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        hideImages();

        imageArray = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        new CountDownTimer(10000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText("Time: " + millisUntilFinished/1000);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {


                timeText.setText("Time's Up!");
                handler.removeCallbacks(runnable);
                scoreText.setText("Score: " + score);
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game");
                alert.setMessage("Are you sure restart game?");

                alert.setPositiveButton("Yes", (dialog, which) -> {

                    // restart the game

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Welcome back :)",Toast.LENGTH_SHORT).show();

                });

                alert.setNegativeButton("No", (dialog, which) -> {

                    // finish the game

                    Toast.makeText(MainActivity.this, "Game Over! I will miss you Dude:(", Toast.LENGTH_SHORT).show();
                    finish();
                });

                alert.show();
            }

        }.start();

    }

    private void hideImages() {

        handler = new Handler();
        runnable = () -> {


            for (ImageView image: imageArray){

                image.setVisibility(View.INVISIBLE);
            }

            // to have the pictures appear on the screen randomly;
            Random random = new Random();
            int i = random.nextInt(9); // 0-8 arasını dahil eder 9 dahil değil.
            imageArray[i].setVisibility(View.VISIBLE);
            handler.postDelayed(runnable,500);

        };

        handler.post(runnable);

    }

    @SuppressLint("SetTextI18n")
    public void clickPicture(View view){

        score++;
        scoreText.setText("Score : " + score);

    }


}