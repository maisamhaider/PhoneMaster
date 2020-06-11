package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.phonemaster.R;

public class SmartChargingAct extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_charging_act);
        preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        ChargingLockedScreenAct chargingLockedScreenAct = new ChargingLockedScreenAct();

        Switch smartCharging_switch = findViewById(R.id.smartCharging_switch);

        ConstraintLayout fullChargedRemind_cl = findViewById(R.id.fullChargedRemind_cl);
        ConstraintLayout fastCharge_cl = findViewById(R.id.fastCharge_cl);

        if (preferences.getBoolean("SMART_CHARGE",false))
        {
            smartCharging_switch.setChecked(true);
        }


        smartCharging_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                editor.putBoolean("SMART_CHARGE",true).commit();
                } else {
                    editor.putBoolean("SMART_CHARGE",false).commit();
//                    PackageManager pm  = getPackageManager();
//                    ComponentName componentName = new ComponentName(SmartChargingAct.this, BroadcastReceiver.class);
//                    pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                            PackageManager.DONT_KILL_APP);

                }
            }
        });

        fullChargedRemind_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmartChargingAct.this,FullChargedRemindAct.class);
                startActivity(intent);
            }
        });
        fastCharge_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmartChargingAct.this,FastChargeAct.class);
                startActivity(intent);
            }
        });
    }
}