package com.example.flybirdgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private ImageView bird, sprite1, sprite2, sprite3, heart1, heart2, heart3, coin1, coin2;
    private TextView textViewScore, textViewTapToPlay;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    boolean beginController= false;
    private Runnable runnable;
    private Handler handler;
    //Position of bird
    int birdX, birdY;
    //dimension of screen
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playSound();

        bird = findViewById(R.id.bird);
        coin1 = findViewById(R.id.imageViewCoin1);
        coin2 = findViewById(R.id.imageViewCoin2);
        sprite1 = findViewById(R.id.imageViewSprite1);
        sprite2 = findViewById(R.id.imageViewSprite2);
        sprite3 = findViewById(R.id.imageViewSprite3);
        heart1 = findViewById(R.id.imageViewHeart1);
        heart2 = findViewById(R.id.imageViewHeart2);
        heart3 = findViewById(R.id.imageViewHeart3);
        textViewScore = findViewById(R.id.textViewScore);
        textViewTapToPlay = findViewById(R.id.textViewStartInfo);
        constraintLayout = findViewById(R.id.constraint_layout);
        getDimensionOfScreen();
        onTouch();

    }

    @Override
    protected void onPause() {
        mediaPlayer.pause();
        super.onPause();
    }

    public void playSound(){
       Intent intent = getIntent();
       boolean soundStatus = intent.getBooleanExtra("play", true);
       if(soundStatus ){
           mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.happy);
           mediaPlayer.start();
       }
    }
    public void getDimensionOfScreen(){
        width = (int) constraintLayout.getWidth();
        height =(int) constraintLayout.getHeight();
    }
    public void onTouch(){
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textViewTapToPlay.setVisibility(View.INVISIBLE);
                if(beginController==false){
                    beginController= true;
                    birdX = (int) bird.getX();
                    birdY = (int) birdY.getY();

                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            handler.postDelayed(runnable, 20);
                        }

                    };
                    handler.post(runnable);
                }
                else{
                    if(motionEvent.getAction() ==MotionEvent.ACTION_DOWN){
                        touchControl = true;
                    }
                    else  if(motionEvent.getAction() ==MotionEvent.ACTION_UP){
                        touchControl = false;
                    }
                }

                return true;
            }
        });
    }



}