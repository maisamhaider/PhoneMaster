package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.BatterySavingAllAppsAdapter;
import com.example.phonemaster.utils.Utils;

import java.util.Calendar;
import java.util.List;

public class CpuCooler extends AppCompatActivity {
    Utils utils;
    TextView coolingTemp_tv;
    ProgressBar cooling_pb;
    ImageView coolingBack_iv;
    private BatterySavingAllAppsAdapter allAppsAdapter;
    ConstraintLayout cpuCoolerSecond_cl, cpuCoolerMain_cl,cpuCooled_cl, coolingMain_cl;
    TextView cpuTemp_tv;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_cooler);
        utils = new Utils(this);
        preferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);


        cpuCoolerSecond_cl = findViewById(R.id.cpuCoolerSecond_cl);
        RecyclerView cpuCoolerApps_rv = findViewById(R.id.cpuCoolerApps_rv);
        LinearLayout cpuCoolBtn_ll = findViewById(R.id.cpuCoolBtn_ll);
        cpuTemp_tv = findViewById(R.id.cpuTemp_tv);
        ImageView cpuCoolingMainBack_iv = findViewById(R.id.cpuCoolingMainBack_iv);
        cpuCooled_cl = findViewById(R.id.cpuCooled_cl);

        String temp = String.format("%.1f",utils.cpuTemperature());
        cpuTemp_tv.setText(temp);

        //scanning cpu
        ConstraintLayout cpuCoolingFirstScreenMain_cl = findViewById(R.id.cpuCoolingFirstScreenMain_cl);
        ImageView cpuCoolingFirstScreenBack_iv = findViewById(R.id.cpuCoolingFirstScreenBack_iv);

        //Cooling apps
        coolingMain_cl = findViewById(R.id.coolingMain_cl);
        coolingTemp_tv = findViewById(R.id.coolingTemp_tv);
        cooling_pb = findViewById(R.id.cooling_pb);
        coolingBack_iv = findViewById(R.id.coolingBack_iv);
        coolingMain_cl.setVisibility(View.GONE);

        //last screen
        cpuCoolerMain_cl = findViewById(R.id.cpuCoolerMain_cl);
        cpuCoolerMain_cl.setVisibility(View.GONE);

        //analyzing apps

        List<String> list = utils.getSystemActiveApps();
        Calendar current = Calendar.getInstance();
        if (preferences.getLong("lastCpuCooledTime", current.getTimeInMillis()) > current.getTimeInMillis()) {
            cpuCoolerSecond_cl.setVisibility(View.GONE);
            String temp1 = String.format("%.1f",utils.cpuTemperature());
            cpuTemp_tv.setText(temp1);
            cpuCooled_cl.setVisibility(View.VISIBLE);

        } else {
            cpuCooled_cl.setVisibility(View.GONE);
            cpuCoolerSecond_cl.setVisibility(View.VISIBLE);
            String temp2 = String.format("%.1f",utils.cpuTemperature());
            cpuTemp_tv.setText(temp2);
         }

        cpuCoolingMainBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        cpuCoolingFirstScreenBack_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cpuCoolingFirstScreenMain_cl.setVisibility(View.GONE);
            }
        }, 4000);


        allAppsAdapter = new BatterySavingAllAppsAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        cpuCoolerApps_rv.setLayoutManager(linearLayoutManager);
        cpuCoolerApps_rv.setAdapter(allAppsAdapter);
        allAppsAdapter.notifyDataSetChanged();


        cpuCoolBtn_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CpuCooler.KillAppsTask appsTask = new KillAppsTask();
                appsTask.execute();

            }
        });


    }

    class KillAppsTask extends AsyncTask<Void, Integer, String> {
        List<String> packageName;
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            packageName = allAppsAdapter.getCheckList();
            coolingMain_cl.setVisibility(View.VISIBLE);
            cooling_pb.setMax(packageName.size());

        }

        @Override
        protected String doInBackground(Void... voids) {
            for (int i = 0; i < packageName.size(); i++) {
                am.killBackgroundProcesses(packageName.get(i));
                publishProgress(i);
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            cooling_pb.setProgress(values[0]);


        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            coolingMain_cl.setVisibility(View.GONE);
            cpuCoolerSecond_cl.setVisibility(View.GONE);
            cpuCoolerMain_cl.setVisibility(View.VISIBLE);
            Calendar nextTime = Calendar.getInstance();
            nextTime.add(Calendar.MINUTE, 5);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putLong("lastCpuCooledTime", nextTime.getTimeInMillis()).commit();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String temp4 = String.format("%.1f",utils.cpuTemperature());
                    cpuTemp_tv.setText( temp4);
                    cpuCoolerMain_cl.setVisibility(View.GONE);
                    cpuCooled_cl.setVisibility(View.VISIBLE);
                    coolingTemp_tv.setText(temp4);

                }
            }, 2000);


        }
    }


    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }
}