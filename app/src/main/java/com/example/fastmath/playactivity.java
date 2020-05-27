package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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

public class playactivity extends AppCompatActivity {
    private int number1, number2, number3, answer, score, bestscoreCurrent, manswer;
    private int lv = 0;
    private boolean isResult, isCheck;
    TextView txvPhepTinh, txvTime, txvScroePlay, txvBestScrore;
    TextView btnTL1, btnTL2, btnTL3;
    CountDownTimer t;
    Question mQuestion;
    SharedPreferences luudiemso;
    Random mrandom;
    Typeface typeface;
    ArrayList<TextView> arrbtnDapAn;
    ArrayList<Integer> arrDapAn;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playactivity);
        anhxa();
        init();
        cauhoi(lv);
        setClick();
    }

    private void setClick() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            score++;
            txvScroePlay.setText(String.valueOf(score));
            lv++;
            cauhoi(lv);
        } else {
            gameover(score, bestscoreCurrent);
        }
    }

    private void init() {
      //  luudiemso = getSharedPreferences("Diemsogame", MODE_PRIVATE);
        mrandom = new Random();
        arrDapAn = new ArrayList<>();
        arrbtnDapAn = new ArrayList<>();
        arrbtnDapAn.add(btnTL1);
        arrbtnDapAn.add(btnTL2);
        arrbtnDapAn.add(btnTL3);
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


    public void luudiem() {
        bestscoreCurrent = luudiemso.getInt("diemcuatoi", 0);
        txvBestScrore.setText("BEST: " + String.valueOf(bestscoreCurrent));
    }

    public void createquestion(int s1, int s2) {

        int dan1, dap2;
        do {
            number1 = mrandom.nextInt(s1);
            number2 = mrandom.nextInt(s2);
            answer = number1 + number2;
            dan1 = mrandom.nextInt(s1) + mrandom.nextInt(number2);
            dap2 = mrandom.nextInt(s1) + mrandom.nextInt(number2);

        } while (answer == dan1 || answer == dap2 || dan1 == dap2);
        String result = number1 + " + " + number2 + " = ?";
        txvPhepTinh.setText(result);
        arrDapAn.add(answer);
        arrDapAn.add(dan1);
        arrDapAn.add(dap2);
        hienthidapan();
        settime();
    }

    public void hienthidapan() {

        ArrayList<Integer> listint = new ArrayList<>();//de random ngau nhien tu 0-2 ma khong trung
        for (int i = 0; i < 3; i++) {
            listint.add(i);

        }
        int n = listint.size();
        ArrayList<Integer> hienthi = new ArrayList<>();
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
        t = new CountDownTimer(6000 + 1, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                txvTime.setText("" + millisUntilFinished / 1000);
                if ((millisUntilFinished / 1000 < 1)) {
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

    void gameover(int score, int bestscore) {
        t.cancel();
        // gui qua overlayout
        Intent intent = new Intent(playactivity.this, overactivity.class);
        intent.putExtra("d", String.valueOf(score));
        intent.putExtra("dcao", String.valueOf(bestscore));
        // gui qua mainlayout
//        Intent intenthome=new Intent(playactivity.this,MainActivity.class);
//        intenthome.putExtra("diemhome",String.valueOf(bestscore));
        finish();
        startActivity(intent);
    }
}
