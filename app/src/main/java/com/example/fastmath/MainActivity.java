package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnAbout;
    TextView txvScore;
    SharedPreferences pr;
    //am thanh
    public SoundPool mysounds;
    public int homesound, clicksound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        //   homesound=mysounds.load(getApplicationContext(),R.raw.homeplay,1);
        clicksound = mysounds.load(getApplicationContext(), R.raw.click, 1);
  //      MediaPlayer homepl = MediaPlayer.create(MainActivity.this, R.raw.homeplay);

     //   float log1=(float)(Math.log(maxVolume-50)/Math.log(maxVolume));
//        homepl.setVolume(1.0f,1.0f); //set volume takes two paramater
//        homepl.setLooping(true);
//        homepl.start();

        pr = getSharedPreferences("luudiemhome", MODE_PRIVATE);
        Intent laydiem = this.getIntent();
        String score = laydiem.getStringExtra("dh");
        txvScore = findViewById(R.id.txvscorehome);
        String ht = pr.getString("diem", "0");


        //luudiem
        SharedPreferences.Editor editor = pr.edit();
        editor.putString("diem", score);
        editor.commit();
        txvScore.setText(ht);

        btnPlay = findViewById(R.id.btnplay);
        btnAbout = findViewById(R.id.btnabout);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, playactivity.class);
                startActivity(intent);
                mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1.0f);

            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1.0f);
            }
        });

    }

    public void homeS() {
        mysounds.play(homesound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
