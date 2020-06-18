package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.BatterySavingAllAppsAdapter;
import com.example.phonemaster.adapters.ProcessesAdapter;
import com.example.phonemaster.async.ProcessesCommonTask;
import com.example.phonemaster.utils.Utils;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.ArrayList;
import java.util.List;

public class BatterySavingAct extends AppCompatActivity {
    Utils utils;
    TextView bbb , bbb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_saving);
        utils = new Utils(this);
        SharedPreferences preferences = getSharedPreferences("myPref",Context.MODE_PRIVATE);

        RecyclerView powerSavingApp_rv = findViewById(R.id.powerSavingApp_rv);
        LinearLayout powerSavingBtn_ll = findViewById(R.id.powerSavingBtn_ll);
        TextView batterySavingRunningApps_tv = findViewById(R.id.batterySavingRunningApps_tv);
        TextView powerSavingMainAppsAmount_tv = findViewById(R.id.powerSavingMainAppsAmount_tv);
        ImageView powerSavingBack_iv = findViewById(R.id.powerSavingBack_iv);

        ConstraintLayout powerSavingFirstScreenMain_cl= findViewById(R.id.powerSavingFirstScreenMain_cl);
        ProgressBar progressBar = findViewById(R.id.roundedHorizontalPSF_pb);
        TextView analyzingBatteryStatusPercent_tv= findViewById(R.id.analyzingBatteryStatusPercent_tv);
        ImageView powerSavingFirstScreenBack_iv= findViewById(R.id.powerSavingFirstScreenBack_iv);
        progressBar.setMax(100);

        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(0);
        animator.setDuration(5_000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                progressBar.setProgress(value);
            }
        });
        animator.start();

        powerSavingBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        powerSavingFirstScreenBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                powerSavingFirstScreenMain_cl.setVisibility(View.GONE);
            }
        },5000);


        List<String> list =  utils.getActiveApps();
        BatterySavingAllAppsAdapter allAppsAdapter = new BatterySavingAllAppsAdapter(this,list);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        powerSavingApp_rv.setLayoutManager(linearLayoutManager);
        powerSavingApp_rv.setAdapter(allAppsAdapter);
        allAppsAdapter.notifyDataSetChanged();


        batterySavingRunningApps_tv.setText( list.size()+" app are Running");
        powerSavingMainAppsAmount_tv.setText(String.valueOf(list.size()));


        powerSavingBtn_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<String> packageName = allAppsAdapter.getCheckList();

                for (int i = 0; i < packageName.size(); i++) {
                    am.killBackgroundProcesses(packageName.get(i));
                }
            }
        });

    }
}