package com.example.flybirdgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView yourScore,highestScore;

    private Button playAgain;
    SharedPreferences sharedPreferences;
    int score, highest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        yourScore = findViewById(R.id.your_score);
        highestScore = findViewById(R.id.highest_score);
        playAgain = findViewById(R.id.play_again);
        setHighestScore();
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void setHighestScore(){
        //get highest score
       sharedPreferences = this.getSharedPreferences("Score", MODE_PRIVATE);
       highest = sharedPreferences.getInt("highestScore", 0);
       //get score
        score = (int) getIntent().getIntExtra("score", 0);
       yourScore.setText("Điểm số của bạn là: " +score);
        highestScore.setText("Điểm số gần cao nhất gần dây là: " + highest);

        if(score> highest){
            sharedPreferences.edit().putInt("highestScore", score).apply();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Thoát game");
        builder.setMessage("Bạn có muốn thoát game?");
        builder.setCancelable(false);
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();

    }


}