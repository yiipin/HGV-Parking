package com.example.hgvparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private long lastClickTime = 0L;
    private static final int FAST_CLICK_DELAY_TIME = 2000;

    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button1 = findViewById(R.id.buttonParking);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClickTime >= FAST_CLICK_DELAY_TIME)
                    openParking();
                lastClickTime = System.currentTimeMillis();
            }
        });

        Button2 = findViewById(R.id.buttonScaling);
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClickTime >= FAST_CLICK_DELAY_TIME)
                    openScaling();
                lastClickTime = System.currentTimeMillis();
            }
        });

        Button3 = findViewById(R.id.buttonService);
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClickTime >= FAST_CLICK_DELAY_TIME)
                    openParking();
                lastClickTime = System.currentTimeMillis();
            }
        });

        Button4 = findViewById(R.id.buttontest);
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClickTime >= FAST_CLICK_DELAY_TIME)
                    openParking();
                lastClickTime = System.currentTimeMillis();
            }
        });
    }
    public void openParking(){
        Intent intent = new Intent(this, Activity_Parking.class);
        startActivity(intent);
    }
    public void openScaling(){
        Intent intent = new Intent(this, Activity_Scaling.class);
        startActivity(intent);
    }
    public void openService(){
        Intent intent = new Intent(this, Activity_Service.class);
        startActivity(intent);
    }
    public void opentest(){
        Intent intent = new Intent(this, Activity_Test.class);
        startActivity(intent);
    }
}