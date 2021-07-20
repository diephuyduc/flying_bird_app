package com.example.flybirdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private ImageView bird, sprite1, sprite2, sprite3, coin, volume;
    private Button btnPlay;
    private Animation animation;
    private MediaPlayer mediaPlayer;
    private boolean soundStatus = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bird = findViewById(R.id.bird);
        sprite1 = findViewById(R.id.sprite1);
        sprite2 = findViewById(R.id.sprite2);
        sprite3 = findViewById(R.id.sprite3);
        coin = findViewById(R.id.coin);
        volume = findViewById(R.id.volume);
        btnPlay = findViewById(R.id.btn_play);
        animation = AnimationUtils.loadAnimation(MainActivity.this
                , R.anim.scale_animtion);
        setAnimation(bird);
        setAnimation(sprite1);
        setAnimation(sprite2);
        setAnimation(sprite3);
        setAnimation(coin);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.happy);
        mediaPlayer.start();
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStatus = !soundStatus;
                if(!soundStatus){
                    mediaPlayer.setVolume(0,0);
                    volume.setImageResource(R.drawable.volume_mute);

                }
                else{
                    mediaPlayer.setVolume(1,1);
                    volume.setImageResource(R.drawable.volume_up);
                }

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("play", soundStatus);
                startActivity(intent);
                mediaPlayer.reset();
                finish();
            }
        });

    }

    public void setAnimation(ImageView view){
        view.setAnimation(animation);
    }
}