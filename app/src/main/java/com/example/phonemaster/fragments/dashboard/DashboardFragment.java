package com.example.phonemaster.fragments.dashboard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonemaster.R;
import com.example.phonemaster.activities.BatterySavingAct;
import com.example.phonemaster.activities.CleanWhatsAppAct;
import com.example.phonemaster.activities.CpuCooler;
import com.example.phonemaster.activities.DeepCleanAct;
import com.example.phonemaster.activities.HarassmentFilterAct;
import com.example.phonemaster.activities.PhoneBoostAct;
import com.example.phonemaster.activities.SmartChargingAct;
import com.example.phonemaster.activities.UnInstallAppAct;
import com.example.phonemaster.activities.WhatsAppStatusAct;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;

import java.util.Objects;


public class DashboardFragment extends Fragment implements View.OnClickListener {
    private static final int REQUEST_PERMISSION = 1000;
    private Permissions permissions;
    private Utils utils;
    private SharedPreferences preferences;
    private int REQUEST_READ_PHONE_STATE = 5005;
    private View root;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init();

        return root;
    }

    public void init(){
        permissions = new Permissions(getActivity());
        permissions.permission();
        utils = new Utils(getActivity());
        preferences = getActivity().getSharedPreferences("myPref",Context.MODE_PRIVATE);

//        Button cleanWhatsApp_btn = root.findViewById(R.id.cleanWhatsApp_btn);
//        Button cpuCooler_btn = root.findViewById(R.id.cpuCooler_btn);
//        Button phoneBoost_btn = root.findViewById(R.id.phoneBoost_btn);
//        Button batterySaving_btn = root.findViewById(R.id.batterySaving_btn);
//        Button whatsAppStatusSaver_btn = root.findViewById(R.id.whatsAppStatusSaver_btn);
//        Button harassmentFilter_btn = root.findViewById(R.id.harassmentFilter_btn);
//        Button unInstallApp_btn = root.findViewById(R.id.unInstallApp_btn);
//        Button deepClean_btn = root.findViewById(R.id.deepClean_btn);
//        Button smartCharging_btn = root.findViewById(R.id.smartCharging_btn);
//        Button junkClean_btn = root.findViewById(R.id.junkClean_btn);

//        cleanWhatsApp_btn.setOnClickListener(this);
//        cpuCooler_btn.setOnClickListener(this);
//        phoneBoost_btn.setOnClickListener(this);
//        batterySaving_btn.setOnClickListener(this);
//        whatsAppStatusSaver_btn.setOnClickListener(this);
//        harassmentFilter_btn.setOnClickListener(this);
//        unInstallApp_btn.setOnClickListener(this);
//        deepClean_btn.setOnClickListener(this);
//        smartCharging_btn.setOnClickListener(this);
//        junkClean_btn.setOnClickListener(this);


        ramAndStorageFun();


    }


    private void ramAndStorageFun()
    {
        TextView ramPercent_tv = root.findViewById(R.id.ramPercent_tv);
        TextView storageUsagePercent_tv = root.findViewById(R.id.storageUsagePercent_tv);
//for Ram
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService( Context.ACTIVITY_SERVICE );
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
//        switch (v.getId())
//        {
//            case R.id.cleanWhatsApp_btn:
//                if (permissions.permission())
//                {
//                    Intent cleanWhatsAppActIntent = new Intent(getActivity(), CleanWhatsAppAct.class);
//                    startActivity(cleanWhatsAppActIntent);
//                }
//                else
//                    Toast.makeText(getActivity(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.junkClean_btn:
//                if (permissions.permission())
//                {
//                    Intent cleanWhatsAppActIntent = new Intent(getActivity(), JunkFilesAct.class);
//                    startActivity(cleanWhatsAppActIntent);
//                }
//                else
//                    Toast.makeText(getActivity(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.cpuCooler_btn:
//                Intent cpuCoolerIntent = new Intent(getActivity(), CpuCooler.class);
//                startActivity(cpuCoolerIntent);
//                break;
//            case R.id.phoneBoost_btn:
//                Intent phoneBoostIntent = new Intent(getActivity(), PhoneBoostAct.class);
//                startActivity(phoneBoostIntent);
//                break;
//            case R.id.batterySaving_btn:
//                Intent batterySavingIntent = new Intent(getActivity(), BatterySavingAct.class);
//                startActivity(batterySavingIntent);
//                break;
//            case R.id.whatsAppStatusSaver_btn:
//                Intent whatsAppStatusSaverIntent = new Intent(getActivity(), WhatsAppStatusAct.class);
//                startActivity(whatsAppStatusSaverIntent);
//                break;
//            case R.id.harassmentFilter_btn:
//                Intent harassmentFilterIntent = new Intent(getActivity(), HarassmentFilterAct.class);
//                startActivity(harassmentFilterIntent);
//                break;
//            case R.id.unInstallApp_btn:
//                Intent unInstallAppIntent = new Intent(getActivity(), UnInstallAppAct.class);
//                startActivity(unInstallAppIntent);
//                break;
//            case R.id.deepClean_btn:
//                Intent deepCleanIntent = new Intent(getActivity(), DeepCleanAct.class);
//                startActivity(deepCleanIntent);
//                break;
//            case R.id.smartCharging_btn:
//                Intent smartChargingIntent = new Intent(getActivity(), SmartChargingAct.class);
//                startActivity(smartChargingIntent);
//                break;
//
//        }
    }

}