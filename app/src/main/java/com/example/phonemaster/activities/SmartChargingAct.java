package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.phonemaster.R;
import com.example.phonemaster.services.MyService;
import com.suke.widget.SwitchButton;

public class SmartChargingAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_charging_act);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        SwitchButton smartCharging_switch = findViewById(R.id.smartCharging_switch);
        ImageView smartChargeBack_iv = findViewById(R.id.smartChargeBack_iv);
        ImageView smartChargeFirstTimeBack_iv = findViewById(R.id.smartChargeFirstTimeBack_iv);
        LinearLayout smartChargeFirstTimeEnable_ll = findViewById(R.id.smartChargeFirstTimeEnable_ll);
        ConstraintLayout smart_charging_first_time = findViewById(R.id.smart_charging_first_time);

        ConstraintLayout fullChargedRemind_cl = findViewById(R.id.fullChargedRemind_cl);
        ConstraintLayout fastCharge_cl = findViewById(R.id.fastCharge_cl);

        boolean isEnable = preferences.getBoolean("IS_SMART_CHARGE_ENABLED", false);
        if (preferences.getBoolean("SMART_CHARGE", false)) {
            smartCharging_switch.setChecked(true);
        }
        if (!isEnable) {
            smart_charging_first_time.setVisibility(View.VISIBLE);
        } else if (isEnable) {
            smart_charging_first_time.setVisibility(View.GONE);
        }
        smartChargeFirstTimeEnable_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("IS_SMART_CHARGE_ENABLED", true).commit();
                smart_charging_first_time.setVisibility(View.GONE);
            }
        });


        smartChargeBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        smartChargeFirstTimeBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        smartCharging_switch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("SMART_CHARGE", true).commit();
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(new Intent(SmartChargingAct.this, MyService.class));
                    } else {
                        startService(new Intent(SmartChargingAct.this, MyService.class));
                    }
                } else {
                    editor.putBoolean("SMART_CHARGE", false).commit();
                    stopService(new Intent(SmartChargingAct.this, MyService.class));

                }
            }
        });

        fullChargedRemind_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmartChargingAct.this, FullChargedRemindAct.class);
                startActivity(intent);
            }
        });
        fastCharge_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmartChargingAct.this, FastChargeAct.class);
                startActivity(intent);
            }
        });
    }
}