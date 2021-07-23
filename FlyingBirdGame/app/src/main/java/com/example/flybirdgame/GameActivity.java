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

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
     private ImageView bird, sprite1, sprite2, sprite3, heart1, heart2, heart3, coin1, coin2;
    private TextView textViewScore, textViewTapToPlay;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    boolean beginController = false;
    private Runnable runnable, runnable1;
    private Handler handler, handler1;
    //Position of bird
    int birdX, birdY;
    //Position of sprite
     int sprite1X, sprite1Y, sprite2X, sprite2Y, sprite3X, sprite3Y, coin1X, coin1Y, coin2X, coin2Y;
    //dimension of screen
    int width, height;
    int right = 3;
    int score=0;
    boolean soundStatus;

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

        onTouch();

    }


    public void playSound() {

        Intent intent = getIntent();
        soundStatus= intent.getBooleanExtra("play", true);
        if (soundStatus) {
            mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.happy);
            mediaPlayer.start();
        }
    }

    public void getDimensionOfScreen() {
        width = (int) constraintLayout.getWidth();
        height = (int) constraintLayout.getHeight();
    }

    public void onTouch() {
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textViewTapToPlay.setVisibility(View.INVISIBLE);
                if (beginController == false) {
                    beginController = true;
                    width = (int) constraintLayout.getWidth();
                    height = (int) constraintLayout.getHeight();
                    birdX = (int) bird.getX();
                    birdY = (int) bird.getY();

                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            moveSprite();
                            moveBird();
                            collision();

                        }

                    };
                    handler.post(runnable);
                } else {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        touchControl = true;
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        touchControl = false;
                    }
                }

                return true;
            }
        });
    }

    public void moveBird() {
        if (touchControl) {
            birdY = (int) (birdY - width / 50);
        } else {
            birdY = (int) (birdY + width / 50);
        }

        if (birdY <= 0) {
            birdY = 0;
        }
        if (birdY >= height - bird.getHeight()) {
            birdY = height - bird.getHeight();
        }
        bird.setY(birdY);
    }

    public void moveSprite() {

        Random random = new Random();
        int s = random.nextInt(150) + 140;
        sprite1 = spriteAction(sprite1, sprite1X, sprite1Y, s);
        sprite1X = (int) sprite1.getX();
        sprite1Y = (int) sprite1.getY();

        int ss = random.nextInt(150) + 120;
        sprite2 = spriteAction(sprite2, sprite2X, sprite2Y, ss);
        sprite2X = (int) sprite2.getX();
        sprite2Y = (int) sprite2.getY();
        int sss = random.nextInt(130) + 120;
        sprite3 = spriteAction(sprite3, sprite3X, sprite3Y, sss);
        sprite3X = (int) sprite3.getX();
        sprite3Y = (int) sprite3.getY();
        int ssss = random.nextInt(135) + 130;
        coin1 = spriteAction(coin1, coin1X, coin1Y, ssss);
        coin1X = (int) coin1.getX();
        coin1Y = (int) coin1.getY();
        int sssss = random.nextInt(150) + 120;
        coin2 = spriteAction(coin2, coin2X, coin2Y, sssss);
        coin2X = (int) coin2.getX();
        coin2Y = (int) coin2.getY();


    }

    public ImageView spriteAction(ImageView view, int x, int y, int s) {


        view.setVisibility(View.VISIBLE);
        System.out.println(view.getX());
        x = (int) (x - (width / s));
        if (x < 0) {
            x = width + 200;
            y = (int) Math.floor(Math.random() * height);
            if (y <= 0) {
                y = 0;
            }
            if (y >= (height - view.getHeight())) {
                y = height - view.getHeight();
            }
        }


        view.setX(x);
        view.setY(y);
        return view;
    }

    public void collision() {
        int center1X = sprite1X + sprite1.getWidth() / 2;
        int center1Y = sprite1Y + sprite1.getHeight() / 2;
        if (center1X >= birdX && center1X <= (birdX + bird.getWidth()) && center1Y >= birdY && center1Y <= (birdY + bird.getHeight())) {
            sprite1X = width + 200;

            right--;
        }

        int center2X = sprite2X + sprite2.getWidth() / 2;
        int center2Y = sprite2Y + sprite2.getHeight() / 2;
        if (center2X >= birdX && center2X <= (birdX + bird.getWidth()) && center2Y >= birdY && center2Y <= (birdY + bird.getHeight())) {
            sprite2X = width + 200;
            right--;
        }

        int center3X = sprite3X + sprite3.getWidth() / 2;
        int center3Y = sprite3Y + sprite3.getHeight() / 2;
        if (center3X >= birdX && center3X <= (birdX + bird.getWidth()) && center3Y >= birdY && center3Y <= (birdY + bird.getHeight())) {
            sprite3X = width + 200;
            right--;
        }



        int centerCoin1X = coin1X + coin1.getWidth() / 2;
        int centerCoin1Y = coin1Y + coin1.getHeight() / 2;
        if (centerCoin1X >= birdX && centerCoin1X <= (birdX + bird.getWidth()) && centerCoin1Y >= birdY && centerCoin1Y <= (birdY + bird.getHeight())) {
            coin1X = width + 200;
            score+=10;
            textViewScore.setText(""+score);
        }

        int centerCoin2X = coin2X + coin2.getWidth() / 2;
        int centerCoin2Y = coin2Y + coin2.getHeight() / 2;
        if (centerCoin2X >= birdX && centerCoin2X <= (birdX + bird.getWidth()) && centerCoin2Y >= birdY && centerCoin2Y <= (birdY + bird.getHeight())) {
            coin2X = width + 200;
            score +=10;
            textViewScore.setText(""+score);
        }
        if(right>0){
            if(right==2){
                heart3.setVisibility(View.INVISIBLE);
            }
            else if(right==1){
                heart2.setVisibility(View.INVISIBLE);
            }
            handler.postDelayed(runnable, 45);
        }


        else{
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            textViewTapToPlay.setVisibility(View.VISIBLE);
            textViewTapToPlay.setText("Tro choi ket thuc");
            sprite1.setVisibility(View.INVISIBLE);
            sprite2.setVisibility(View.INVISIBLE);
            sprite3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);
            heart1.setVisibility(View.INVISIBLE);

            handler1 = new Handler();
            runnable1 = new Runnable() {
                @Override
                public void run() {
                    birdX += width/300;
                    birdY = (int) height/2;
                    bird.setY(birdY);
                    bird.setX(birdX);
                    if(birdX<width){
                        handler1.postDelayed(runnable1, 20);
                    }
                    else{
                        handler1.removeCallbacks(runnable1);
                        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                        if(soundStatus){
                            mediaPlayer.stop();
                        }
                        finish();
                    }

                }
            };
            handler1.post(runnable1);


        }

    }


}