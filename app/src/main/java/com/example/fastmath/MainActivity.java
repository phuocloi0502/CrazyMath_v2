package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btnPlay, btnAbout;
    TextView txvScore,txvNamegame,txvNamegame1,txvBestcore,txvScorehome;
    SharedPreferences pr;
    Integer score;
    //am thanh
    public SoundPool mysounds;
    public int clicksound;
    public MediaPlayer homepl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();
        anhxa();
        setfont();
        setClick();

    }

    private void setfont() {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font/UVNBanhMi.TTF");
        txvNamegame.setTypeface(typeface);
        txvNamegame1.setTypeface(typeface);
        txvBestcore.setTypeface(typeface);
        txvScorehome.setTypeface(typeface);
        btnPlay.setTypeface(typeface);
        btnAbout.setTypeface(typeface);
    }

    public void setClick() {
        btnPlay.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnplay: {

                soundClick();
                Intent intent = new Intent(MainActivity.this, playactivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnabout: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                soundClick();
                break;
            }
            default: {

            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        soundHome();
        showbestscore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homepl.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        homepl.pause();
    }

    public void init() {
        pr = getSharedPreferences("Diemsogame", Context.MODE_PRIVATE);
        mysounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        clicksound = mysounds.load(getApplicationContext(), R.raw.click_2, 1);

    }

    public void soundClick() {
        mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1);
    }

    public void soundHome() {
        homepl = MediaPlayer.create(MainActivity.this, R.raw.homeplay);
        homepl.setVolume(1.0f, 1.0f); //set volume takes two paramater
        homepl.setLooping(true);
        homepl.start();
    }

    public void anhxa() {
        txvScore = findViewById(R.id.txvscorehome);
        btnPlay = findViewById(R.id.btnplay);
        btnAbout = findViewById(R.id.btnabout);
        txvNamegame=findViewById(R.id.txvnamegame);
        txvNamegame1=findViewById(R.id.txvnamegame1);
        txvBestcore=findViewById(R.id.txvbestcore);
        txvScorehome=findViewById(R.id.txvscorehome);
    }

    public void showbestscore() {
        score = pr.getInt("Diemsogame", 0);
        txvScore.setText("" + score);
    }

}
