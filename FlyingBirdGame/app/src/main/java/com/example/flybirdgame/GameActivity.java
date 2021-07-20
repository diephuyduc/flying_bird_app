package com.example.flybirdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playSound();

    }
   public void playSound(){
       Intent intent = getIntent();
       boolean soundStatus = intent.getBooleanExtra("play", true);
       if(soundStatus ){
           mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.happy);
           mediaPlayer.start();
       }
    }
}