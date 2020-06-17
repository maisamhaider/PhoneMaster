package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.BatterySavingAllAppsAdapter;
import com.example.phonemaster.adapters.ProcessesAdapter;
import com.example.phonemaster.async.ProcessesCommonTask;
import com.example.phonemaster.utils.Utils;

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