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
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btnAbout;
    TextView txvChonpt, txvBestCong, txvBesTru, txvBestNhan, txvBestChia;
    ImageView btnCong, btnTru, btnNhan, btnChia;
    SharedPreferences pr;
    Integer scorecong,scoretru,scorenhan,scorechia;
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

    public void anhxa() {
        btnAbout=findViewById(R.id.btnabout);
        btnCong=findViewById(R.id.btncong);
        btnTru=findViewById(R.id.btntru);
        btnNhan=findViewById(R.id.btnnhan);
        btnChia=findViewById(R.id.btnchia);
        txvChonpt=findViewById(R.id.txvchonpt);
        txvBestCong=findViewById(R.id.txvbestcong);
        txvBesTru=findViewById(R.id.txvbesttru);
        txvBestNhan=findViewById(R.id.txvbestnhan);
        txvBestChia=findViewById(R.id.txvbestchia);
    }

    private void setfont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/UVNBanhMi.TTF");
        txvChonpt.setTypeface(typeface);
    }

    public void setClick() {
        btnAbout.setOnClickListener(this);
        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnabout: {
                soundClick();
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btncong: {
                Intent intent = new Intent(MainActivity.this, playactivity.class);
                startActivity(intent);
                soundClick();
                break;
            }
            case R.id.btntru: {
                Intent intent = new Intent(MainActivity.this, PlayTru.class);
                startActivity(intent);
                soundClick();
                break;
            }
            case R.id.btnnhan: {
                Intent intent = new Intent(MainActivity.this, PlayNhan.class);
                startActivity(intent);
                soundClick();
                break;
            }
            case R.id.btnchia: {
                Intent intent = new Intent(MainActivity.this, PlayChia.class);
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

    public void showbestscore() {
        scorecong = pr.getInt("bestcong", 0);
        scoretru = pr.getInt("besttru", 0);
        scorenhan = pr.getInt("bestnhan", 0);
        scorechia = pr.getInt("bestchia", 0);
        txvBestCong.setText("BEST: " + scorecong);
        txvBesTru.setText("BEST: " + scoretru);
        txvBestNhan.setText("BEST: " + scorenhan);
        txvBestChia.setText("BEST: " + scorechia);
    }

}
