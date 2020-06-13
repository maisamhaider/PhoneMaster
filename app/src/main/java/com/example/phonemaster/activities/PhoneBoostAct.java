package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

public class PhoneBoostAct extends AppCompatActivity {
    Utils utils;
    TextView textView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_boost);
        utils = new Utils(this);

        textView = findViewById(R.id.uuu);
//        for (int i = 0; i < utils.getActiveApps(PhoneBoostAct.this).size(); i++)
//        {
//            textView.setText(textView.getText()  +utils.getActiveApps(PhoneBoostAct.this).get(i)+"\n\n" );
//        }
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        am.killBackgroundProcesses(PACKAGENAME);
    }

}