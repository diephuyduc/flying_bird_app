package com.example.flybirdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private ImageView bird, sprite1, sprite2, sprite3, coin, volume;
    private Button btnPlay;
    private Animation animation;
    private MediaPlayer mediaPlayer;
    private boolean soundStatus;
    private SharedPreferences sharedPreferences;
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
        getSound();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this, R.raw.happy);
        getSound();
        if(soundStatus ==true){
            mediaPlayer.start();

        }
        Toast.makeText(this, ""+soundStatus, Toast.LENGTH_LONG).show();

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundStatus = !soundStatus;
                if(!soundStatus){
                    mediaPlayer.pause();
                    volume.setImageResource(R.drawable.volume_mute);

                }
                else{
                    mediaPlayer.start();
                    mediaPlayer.setVolume(1,1);
                    volume.setImageResource(R.drawable.volume_up);
                }
                setSound();

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
    public void getSound(){
        sharedPreferences = this.getSharedPreferences("sound", MODE_PRIVATE);
        soundStatus = sharedPreferences.getBoolean("sound", true);
        if(soundStatus == true){
            volume.setImageResource(R.drawable.volume_up);
        }
        else{
            volume.setImageResource(R.drawable.volume_mute);
        }

    }
    public void setSound(){
        sharedPreferences = this.getSharedPreferences("sound", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("sound", soundStatus).apply();
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("On pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.reset();
    }

    public void setAnimation(ImageView view){
        view.setAnimation(animation);
    }
}