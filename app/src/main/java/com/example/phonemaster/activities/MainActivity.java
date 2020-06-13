package com.example.phonemaster.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.services.MyService;
import com.example.phonemaster.utils.Utils;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PERMISSION = 1000;
    private Permissions permissions;
    private Utils utils;
    private SharedPreferences preferences;
    private int REQUEST_READ_PHONE_STATE = 5005;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissions = new Permissions(this);
        permissions.permission();
        utils = new Utils(this);
        preferences = getSharedPreferences("myPref",Context.MODE_PRIVATE);

        Button cleanWhatsApp_btn = findViewById(R.id.cleanWhatsApp_btn);
        Button cpuCooler_btn = findViewById(R.id.cpuCooler_btn);
        Button phoneBoost_btn = findViewById(R.id.phoneBoost_btn);
        Button batterySaving_btn = findViewById(R.id.batterySaving_btn);
        Button whatsAppStatusSaver_btn = findViewById(R.id.whatsAppStatusSaver_btn);
        Button harassmentFilter_btn = findViewById(R.id.harassmentFilter_btn);
        Button unInstallApp_btn = findViewById(R.id.unInstallApp_btn);
        Button deepClean_btn = findViewById(R.id.deepClean_btn);
        Button smartCharging_btn = findViewById(R.id.smartCharging_btn);


//        if(Build.VERSION.SDK_INT >25){
//            startForegroundService(new Intent(this, Service.class));
//        }else{
//            startService(new Intent(this, Service.class));
//        }
//        if (preferences.getBoolean("SMART_CHARGE",false))
//        {
//            FastChargingChargerReceiver fastChargingChargerReceiver = new FastChargingChargerReceiver();
//            IntentFilter intentFilter = new IntentFilter( Intent.ACTION_BATTERY_CHANGED );
//            getApplicationContext().registerReceiver( fastChargingChargerReceiver, intentFilter );
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(new Intent(this, MyService.class));
        }else{
            startService(new Intent(this, MyService.class));
        }

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            Toast.makeText(this, "Read phone state", Toast.LENGTH_SHORT).show();
        }

        cleanWhatsApp_btn.setOnClickListener(this);
        cpuCooler_btn.setOnClickListener(this);
        phoneBoost_btn.setOnClickListener(this);
        batterySaving_btn.setOnClickListener(this);
        whatsAppStatusSaver_btn.setOnClickListener(this);
        harassmentFilter_btn.setOnClickListener(this);
        unInstallApp_btn.setOnClickListener(this);
        deepClean_btn.setOnClickListener(this);
        smartCharging_btn.setOnClickListener(this);
        ramAndStorageFun();

    }

    private void ramAndStorageFun()
    {
        TextView ramPercent_tv = findViewById(R.id.ramPercent_tv);
        TextView storageUsagePercent_tv = findViewById(R.id.storageUsagePercent_tv);
//for Ram
        ActivityManager activityManager = (ActivityManager) Objects.requireNonNull( this ).getSystemService( Context.ACTIVITY_SERVICE );
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo( memoryInfo );
        float totalRam1 = memoryInfo.totalMem / 1024 / 1024;
        float freeRam1 = memoryInfo.availMem / 1024/ 1024;
        float usedRam =  totalRam1 - freeRam1;
        ramPercent_tv.setText( String.format( "%.1f",utils.getPercentage( totalRam1,usedRam ) )+"%" );
        //for Storage
        float totalStorageInGBs, availableStorageInGBs, usedStorageInGBs;

        totalStorageInGBs = (utils.getTotalStorage() / 1024) + 1;
        availableStorageInGBs = utils.getAvailableStorage() / 1024;

        usedStorageInGBs = totalStorageInGBs - availableStorageInGBs;
        storageUsagePercent_tv.setText(String.format( "%.1f", utils.getPercentage( totalStorageInGBs, usedStorageInGBs )  )+ "%" );

     }

    @Override
    public void onClick(View v) {
        switch (v.getId())
            {
                case R.id.cleanWhatsApp_btn:
                    if (permissions.permission())
                    {
                        Intent cleanWhatsAppActIntent = new Intent(this, CleanWhatsAppAct.class);
                        startActivity(cleanWhatsAppActIntent);
                    }
                    else
                        Toast.makeText(this, "Permission is not granted ", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cpuCooler_btn:
                    Intent cpuCoolerIntent = new Intent(this, CpuCooler.class);
                    startActivity(cpuCoolerIntent);
                    break;
                case R.id.phoneBoost_btn:
                    Intent phoneBoostIntent = new Intent(this, PhoneBoostAct.class);
                    startActivity(phoneBoostIntent);
                    break;
                case R.id.batterySaving_btn:
                    Intent batterySavingIntent = new Intent(this, BatterySavingAct.class);
                    startActivity(batterySavingIntent);
                    break;
                case R.id.whatsAppStatusSaver_btn:
                    Intent whatsAppStatusSaverIntent = new Intent(this, WhatsAppStatusAct.class);
                    startActivity(whatsAppStatusSaverIntent);
                    break;
                case R.id.harassmentFilter_btn:
                    Intent harassmentFilterIntent = new Intent(this, HarassmentFilterAct.class);
                    startActivity(harassmentFilterIntent);
                    break;
                case R.id.unInstallApp_btn:
                    Intent unInstallAppIntent = new Intent(this, UnInstallAppAct.class);
                    startActivity(unInstallAppIntent);
                    break;
                case R.id.deepClean_btn:
                    Intent deepCleanIntent = new Intent(this, DeepCleanAct.class);
                    startActivity(deepCleanIntent);
                    break;
                case R.id.smartCharging_btn:
                    Intent smartChargingIntent = new Intent(this, SmartChargingAct.class);
                    startActivity(smartChargingIntent);
                    break;

            }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "Permission is required for working application properly ",
                        Toast.LENGTH_SHORT).show();
                Log.i("Calendar Permission", "Not Granted");
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}