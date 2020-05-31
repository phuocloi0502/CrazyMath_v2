package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PlayNhan extends AppCompatActivity {
    public int number1, number2, answer, score, bestscoreCurrent;
    private int lv = 0;
    private boolean isCheck;
    public String pt = "";
    TextView txvPhepTinh, txvTime, txvScroePlay, txvBestScrore;
    TextView btnTL1, btnTL2, btnTL3;
    CountDownTimer t;
    SharedPreferences luudiemso;
    Random mrandom;
    Typeface typeface;
    ArrayList<TextView> arrbtnDapAn;
    ArrayList<Integer> arrDapAn;
    ArrayList<Integer> listint;
    ArrayList<Integer> hienthi;
    public SoundPool mysounds;
    public int rightsound, wrongsound, clicksound;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playactivity);
        anhxa();
        setfont();
        init();
        sethienbutton();
        showbestscore();
        cauhoi(lv);
        setClick();
    }

    private void setfont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/UVNBanhMi.TTF");
        txvScroePlay.setTypeface(typeface);
        txvBestScrore.setTypeface(typeface);
    }

    private void setClick() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setanbutton();
                kiemtra((TextView) v);
            }

        };
        for (TextView t : arrbtnDapAn) {
            t.setOnClickListener(listener);
        }
    }

    private void kiemtra(TextView btn) {
        int gt = Integer.parseInt(btn.getText().toString());
        if (gt == answer) {
            t.cancel();
            soundRight();
            arrDapAn.clear();
            hienthi.clear();
            listint.clear();

            lv++;
            new CountDownTimer(700, 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    score++;
                    txvScroePlay.setText("SCORE: " + String.valueOf(score));
                    if (bestscoreCurrent < score) {
                        luudiem();
                    }
                    sethienbutton();
                    cauhoi(lv);
                }
            }.start();

        } else {
            soundWrong();
            new CountDownTimer(700, 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    gameover(score, bestscoreCurrent);
                }
            }.start();
        }
    }

    private void soundRight() {
        mysounds.play(rightsound, 1.0f, 1.0f, 1, 0, 1);
    }

    private void soundWrong() {
        mysounds.play(wrongsound, 1.0f, 1.0f, 1, 0, 1);
    }

    private void init() {
        luudiemso = getSharedPreferences("Diemsogame", Context.MODE_PRIVATE);
        mrandom = new Random();
        arrDapAn = new ArrayList<>();
        arrbtnDapAn = new ArrayList<>();
        arrbtnDapAn.add(btnTL1);
        arrbtnDapAn.add(btnTL2);
        arrbtnDapAn.add(btnTL3);
        listint = new ArrayList<>();
        hienthi = new ArrayList<>();
        mysounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        clicksound = mysounds.load(getApplicationContext(), R.raw.click_2, 1);
        wrongsound = mysounds.load(getApplicationContext(), R.raw.wrong, 1);
        rightsound = mysounds.load(getApplicationContext(), R.raw.right, 1);
    }

    public void anhxa() {
        txvTime = findViewById(R.id.txvtime);
        txvScroePlay = findViewById(R.id.txvscoreplay);
        txvBestScrore = findViewById(R.id.txvbestscoreplay);
        txvPhepTinh = findViewById(R.id.txvpheptinh);
        btnTL1 = findViewById(R.id.btntl1);
        btnTL2 = findViewById(R.id.btntl2);
        btnTL3 = findViewById(R.id.btntl3);
    }


    public void showbestscore() {
        bestscoreCurrent = luudiemso.getInt("bestnhan", 0);
        txvBestScrore.setText("BEST: " + String.valueOf(bestscoreCurrent));
    }

    public void luudiem() {
        SharedPreferences.Editor editor = luudiemso.edit();
        editor.putInt("bestnhan", score);
        editor.commit();
    }


    public void createquestion(int s1, int s2) {
        int dan1, dap2, stt;
        String result = "";
        stt = mrandom.nextInt(3);
        number1 = mrandom.nextInt(s1);
        number2 = mrandom.nextInt(s2);
        answer = number1 * number2;
        switch (stt) {
            case 0: {
                dan1 = answer + 1;
                dap2 = answer - 1;
                arrDapAn.add(answer);
                arrDapAn.add(dan1);
                arrDapAn.add(dap2);
                break;
            }
            case 1: {
                dan1 = answer - 2;
                dap2 = answer - 1;
                arrDapAn.add(answer);
                arrDapAn.add(dan1);
                arrDapAn.add(dap2);
                break;
            }
            case 2: {
                dan1 = answer + 2;
                dap2 = answer + 1;
                arrDapAn.add(answer);
                arrDapAn.add(dan1);
                arrDapAn.add(dap2);
                break;
            }
            default: {
                Toast.makeText(PlayNhan.this, "Toang roi !", Toast.LENGTH_SHORT).show();
            }
        }
        result = number1 + " x " + number2 + " = ?";
        txvPhepTinh.setText(result);
        hienthidapan();
        settime();


    }

    public void hienthidapan() {

        //de random ngau nhien tu 0-2 ma khong trung
        for (int i = 0; i < 3; i++) {
            listint.add(i);

        }
        int n = listint.size();
        while (n > 0) {
            int vt = listint.remove(mrandom.nextInt(n));//random ngau nhien tu 0-2 ma khong trung
            Integer a = arrDapAn.get(vt);//lay gia tri ngau nhien trong arrDapAn
            hienthi.add(a);// them ket qua ngau nhien trong arrtxvdapan
            n--;
        }

        for (int i = 0; i < arrbtnDapAn.size(); i++) {
            arrbtnDapAn.get(i).setText(String.valueOf(hienthi.get(i)));
        }
    }

    public void cauhoi(int lv) {
        if (lv <= 10) {
            createquestion(10, 10);
        }
        if (lv > 10 && lv <= 20) {
            createquestion(30, 30);
        }
        if (lv > 20) {
            createquestion(100, 100);
        }
    }

    public void settime() {
        t = new CountDownTimer(4000 + 1, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                txvTime.setText("" + millisUntilFinished / 1000);
                if ((millisUntilFinished / 1000 < 1)) {
                    setanbutton();
                    txvTime.setText("Hết giờ");

                }
            }

            @Override
            public void onFinish() {
                gameover(score, bestscoreCurrent);
                //playactivity.this.finish();
            }
        };
        t.start();
    }

    public void gameover(int score, int bestscore) {
        if (bestscore < score) {
            luudiem();
        }
        t.cancel();
        Intent intent = new Intent(PlayNhan.this, OverNhan.class);
        intent.putExtra("d", String.valueOf(score));
        finish();
        startActivity(intent);
    }

    public void setanbutton() {
        btnTL1.setEnabled(false);
        btnTL2.setEnabled(false);
        btnTL3.setEnabled(false);


    }

    public void sethienbutton() {
        btnTL1.setEnabled(true);
        btnTL2.setEnabled(true);
        btnTL3.setEnabled(true);
    }

}
