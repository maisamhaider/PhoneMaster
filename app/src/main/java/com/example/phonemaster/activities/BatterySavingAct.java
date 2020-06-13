package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

public class BatterySavingAct extends AppCompatActivity {
    Utils utils;
    TextView bbb , bbb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_saving);
//        utils = new Utils(this);
//        bbb = findViewById(R.id.bbb);
//        for (int i = 0; i < utils.recentApps().size(); i++) {
//            bbb.setText("Application executed: " + utils.recentApps().get(i).pkgList.length + "\n\n");
//        }

    }
}