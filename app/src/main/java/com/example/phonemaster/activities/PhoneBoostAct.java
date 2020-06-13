package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanPackagesAdapter;
import com.example.phonemaster.adapters.ProcessesAdapter;
import com.example.phonemaster.async.DeepCleanPkgsTask;
import com.example.phonemaster.async.ProcessesCommonTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PhoneBoostAct extends AppCompatActivity {
    Utils utils;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_boost);

        utils = new Utils(this);
        RecyclerView phoneBoostApps_rv = findViewById(R.id.phoneBoostApps_rv);
        Button phoneBoostAppsClean_btn = findViewById(R.id.phoneBoostAppsClean_btn);

        ProcessesAdapter processesAdapter = new ProcessesAdapter(this);
        ProcessesCommonTask processesCommonTask = new ProcessesCommonTask(this, processesAdapter, phoneBoostApps_rv);
        processesCommonTask.execute();

         phoneBoostAppsClean_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<String> packageName = processesAdapter.getSendList();

                for (int i = 0; i < packageName.size(); i++) {
                    am.killBackgroundProcesses(packageName.get(i));
                }
            }
        });

    }


}