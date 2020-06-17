package com.example.fastmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    Button btnBack;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
//        btnBack=findViewById(R.id.btnback);
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        t1=findViewById(R.id.txv1);
        t2=findViewById(R.id.txv2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/UVNBanhMi.TTF");
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
    }
}
