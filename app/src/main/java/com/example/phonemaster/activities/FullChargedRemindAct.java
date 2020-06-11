package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.phonemaster.R;

public class FullChargedRemindAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_charged_remind);
        SharedPreferences preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Switch fullChargedAlarm_switch, fullChargedRemindSound_switch, fullChargedRemindVibrate_switch, fullChargedRemindDoNotDisturb_switch;

        fullChargedAlarm_switch = findViewById(R.id.fullChargedAlarm_switch);
        fullChargedRemindSound_switch = findViewById(R.id.fullChargedRemindSound_switch);
        fullChargedRemindVibrate_switch = findViewById(R.id.fullChargedRemindVibrate_switch);
        fullChargedRemindDoNotDisturb_switch = findViewById(R.id.fullChargedRemindDoNotDisturb_switch);

        fullChargedAlarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fullChargedRemindSound_switch.setChecked(true);
                    fullChargedRemindVibrate_switch.setChecked(true);
                    fullChargedRemindDoNotDisturb_switch.setChecked(true);
                    editor.putBoolean("FULL_CHARGED_ALARM", true).commit();
                    editor.putBoolean("FULL_CHARGED_SOUND", true).commit();
                    editor.putBoolean("FULL_CHARGED_VIBRATE", true).commit();
                    editor.putBoolean("FULL_CHARGED_DO_NOT_DISTURB", true).commit();

                } else {
                    fullChargedRemindSound_switch.setChecked(false);
                    fullChargedRemindVibrate_switch.setChecked(false);
                    fullChargedRemindDoNotDisturb_switch.setChecked(false);
                    editor.putBoolean("FULL_CHARGED_ALARM", false).commit();
                    editor.putBoolean("FULL_CHARGED_SOUND", false).commit();
                    editor.putBoolean("FULL_CHARGED_VIBRATE", false).commit();
                    editor.putBoolean("FULL_CHARGED_DO_NOT_DISTURB", false).commit();

                }
            }
        });

        fullChargedRemindSound_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("FULL_CHARGED_SOUND", true).commit();

                } else {
                    editor.putBoolean("FULL_CHARGED_SOUND", false).commit();

                }
            }
        });
        fullChargedRemindVibrate_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("FULL_CHARGED_VIBRATE", true).commit();

                } else {
                    editor.putBoolean("FULL_CHARGED_VIBRATE", false).commit();

                }
            }
        });
        fullChargedRemindDoNotDisturb_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("FULL_CHARGED_DO_NOT_DISTURB", true).commit();

                } else {
                    editor.putBoolean("FULL_CHARGED_DO_NOT_DISTURB", false).commit();

                }
            }
        });



    }
}