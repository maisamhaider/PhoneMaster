package com.example.phonemaster.fragments.dashboard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.app.adprogressbarlib.AdCircleProgress;
import com.example.phonemaster.R;
import com.example.phonemaster.activities.CleanWhatsAppAct;
import com.example.phonemaster.activities.UnInstallAppAct;
import com.example.phonemaster.activities.WhatsAppStatusAct;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_PERMISSION = 1000;
    private Permissions permissions;
    private Utils utils;
    private SharedPreferences preferences;
    private int REQUEST_READ_PHONE_STATE = 5005;
    private View root;
    private AdCircleProgress cpbRam, cpbStorage;
    private ProgressBar pbRam, pbStorage;
    private TextView tvRamInfo, tvStorageInfo;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init();

        return root;
    }

    public void init() {
        permissions = new Permissions(getActivity());
        permissions.permission();
        utils = new Utils(getActivity());
        preferences = getActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE);

        cpbRam = root.findViewById(R.id.pgb_ram);
        cpbStorage = root.findViewById(R.id.pgb_storage);

        pbRam = root.findViewById(R.id.pb_ram);
        pbStorage = root.findViewById(R.id.pb_storage);
        tvRamInfo = root.findViewById(R.id.tv_ram_info);
        tvStorageInfo = root.findViewById(R.id.tv_storage_info);

        ImageView ivAppCleanup = root.findViewById(R.id.iv_app_cleanup);
        ImageView ivPowerSaving = root.findViewById(R.id.iv_power_saving);
        ImageView ivStatusSaver = root.findViewById(R.id.iv_status_saver);

        ivAppCleanup.setOnClickListener(this);
        ivPowerSaving.setOnClickListener(this);
        ivStatusSaver.setOnClickListener(this);


        ramAndStorageFun();


    }


    private void ramAndStorageFun() {
//        TextView ramPercent_tv = root.findViewById(R.id.ramPercent_tv);
//        TextView storageUsagePercent_tv = root.findViewById(R.id.storageUsagePercent_tv);
//for Ram
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        float totalRam1 = memoryInfo.totalMem / 1024 / 1024;
        float freeRam1 = memoryInfo.availMem / 1024 / 1024;
        float usedRam = totalRam1 - freeRam1;
//        ramPercent_tv.setText( String.format( "%.1f",utils.getPercentage( totalRam1,usedRam ) )+"%" );
        cpbRam.setAdProgress((int) utils.getPercentage( totalRam1,usedRam ));
        pbRam.setProgress((int) utils.getPercentage( totalRam1,usedRam ));
        tvRamInfo.setText(String.format("%.0fMB/%.0fMB",usedRam,totalRam1));


        //for Storage
        float totalStorageInGBs, availableStorageInGBs, usedStorageInGBs;

        totalStorageInGBs = (utils.getTotalStorage() / 1024) + 1;
        availableStorageInGBs = utils.getAvailableStorage() / 1024;

        usedStorageInGBs = totalStorageInGBs - availableStorageInGBs;

        cpbStorage.setAdProgress((int) utils.getPercentage( totalStorageInGBs, usedStorageInGBs ));
        pbStorage.setProgress((int) utils.getPercentage( totalStorageInGBs, usedStorageInGBs ));
        tvStorageInfo.setText(String.format("%.0fGB/%.0fGB",usedStorageInGBs,totalStorageInGBs));


//        storageUsagePercent_tv.setText(String.format( "%.1f", utils.getPercentage( totalStorageInGBs, usedStorageInGBs )  )+ "%" );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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
            case R.id.iv_app_cleanup:
                Intent unInstallAppIntent = new Intent(getActivity(), UnInstallAppAct.class);
                startActivity(unInstallAppIntent);
                break;
            case R.id.iv_power_saving:
                Intent cwpInstallAppIntent = new Intent(getActivity(), CleanWhatsAppAct.class);
                startActivity(cwpInstallAppIntent);
                break;
            case R.id.iv_status_saver:
                Intent cSSInstallAppIntent = new Intent(getActivity(), WhatsAppStatusAct.class);
                startActivity(cSSInstallAppIntent);
                break;
//            case R.id.deepClean_btn:
//                Intent deepCleanIntent = new Intent(getActivity(), DeepCleanAct.class);
//                startActivity(deepCleanIntent);
//                break;
//            case R.id.smartCharging_btn:
//                Intent smartChargingIntent = new Intent(getActivity(), SmartChargingAct.class);
//                startActivity(smartChargingIntent);
//                break;
//
        }
    }

}