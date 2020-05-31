package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OverTru extends AppCompatActivity {
    TextView txvmyScrore,txvYourcore,txvGameover;
    Button btn_Try, btn_Home;
    String myCore,mybest;
    SoundPool mysounds;
    public int clicksound;
    public MediaPlayer overl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overactivity);
        txvmyScrore = findViewById(R.id.txvmyscore);
        txvYourcore=findViewById(R.id.txvyourcore);
        txvGameover=findViewById(R.id.txvgameover);
        btn_Home = findViewById(R.id.btnhome);
        btn_Try = findViewById(R.id.btntryagain);

        setfont();

        mysounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        clicksound = mysounds.load(getApplicationContext(), R.raw.click_2, 1);
        // lay diem
        Intent getScrore = this.getIntent();
        myCore = getScrore.getStringExtra("d");
        mybest = getScrore.getStringExtra("dcao");
        //hien diem
        txvmyScrore.setText(myCore);
        btn_Try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1);
                Intent intent=new Intent(OverTru.this,PlayTru.class);
                startActivity(intent);
                OverTru.this.finish();
            }
        });
        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mysounds.play(clicksound, 1.0f, 1.0f, 1, 0, 1);
                Intent intent=new Intent(OverTru.this,MainActivity.class);
                intent.putExtra("BestScore",mybest);
                startActivity(intent);
                OverTru.this.finish();
            }
        });
    }

    private void setfont() {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"font/UVNBanhMi.TTF");
        txvmyScrore.setTypeface(typeface);
        txvYourcore.setTypeface(typeface);
        txvGameover.setTypeface(typeface);
        btn_Try.setTypeface(typeface);
        btn_Home.setTypeface(typeface);
    }

    @Override
    protected void onStart() {
        super.onStart();
        soundHome();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overl.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overl.pause();
    }

    public void soundHome() {
        overl = MediaPlayer.create(OverTru.this, R.raw.wrong2);
        overl.setVolume(1.0f, 1.0f); //set volume takes two paramater
        overl.setLooping(false);
        overl.start();
    }
}
