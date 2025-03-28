package com.cleaner.booster.phone.repairer.app.fragments.dashboard;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.app.adprogressbarlib.AdCircleProgress;
import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.activities.BaseActivity;
import com.cleaner.booster.phone.repairer.app.activities.BatterySavingAct;
import com.cleaner.booster.phone.repairer.app.activities.CleanWhatsAppAct;
import com.cleaner.booster.phone.repairer.app.activities.CpuCooler;
import com.cleaner.booster.phone.repairer.app.activities.DeepCleanAct;
import com.cleaner.booster.phone.repairer.app.activities.InternetSpeedAct;
import com.cleaner.booster.phone.repairer.app.activities.JunkFilesAct;
import com.cleaner.booster.phone.repairer.app.activities.PhoneBoostAct;
import com.cleaner.booster.phone.repairer.app.activities.RepairAct;
import com.cleaner.booster.phone.repairer.app.activities.SmartChargingAct;
import com.cleaner.booster.phone.repairer.app.activities.UnInstallAppAct;
import com.cleaner.booster.phone.repairer.app.activities.WhatsAppStatusAct;
import com.cleaner.booster.phone.repairer.app.fragments.BaseFragment;
import com.cleaner.booster.phone.repairer.app.permission.Permissions;
import com.cleaner.booster.phone.repairer.app.utils.Utils;


public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    private Permissions permissions;
    private Utils utils;
    private View root;
    private AdCircleProgress cpbRam, cpbStorage;
    private ProgressBar pbRam, pbStorage;
    private TextView tvRamInfo, tvStorageInfo, tv_num;
//    private Button b_optimize;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        init();

        return root;
    }

    public void init() {
        permissions = new Permissions(getContext());
        permissions.permission();
        utils = new Utils(getContext());
        SharedPreferences preferences = getContext().getSharedPreferences("myPref", Context.MODE_PRIVATE);

        cpbRam = root.findViewById(R.id.pgb_ram);
        cpbStorage = root.findViewById(R.id.pgb_storage);
//        b_optimize = root.findViewById(R.id.b_optimize);

        pbRam = root.findViewById(R.id.pb_ram);
        pbStorage = root.findViewById(R.id.pb_storage);
        tvRamInfo = root.findViewById(R.id.tv_ram_info);
        tvStorageInfo = root.findViewById(R.id.tv_storage_info);
        tv_num = root.findViewById(R.id.tv_num);

        ImageView ivAppCleanup = root.findViewById(R.id.iv_app_cleanup);
        ImageView ivPowerSaving = root.findViewById(R.id.iv_power_saving);
        ImageView ivStatusSaver = root.findViewById(R.id.iv_status_saver);
        ImageView ivJunkFile = root.findViewById(R.id.iv_junk_file);
        ImageView ivCpuCooler = root.findViewById(R.id.iv_cpu_cooler);
        ImageView ivBoostPhone = root.findViewById(R.id.iv_boost_phone);

        ConstraintLayout photoVideo_cl = root.findViewById(R.id.photoVideo_cl);
        ConstraintLayout cleanWhatsApp_cl = root.findViewById(R.id.cleanWhatsApp_cl);
        ConstraintLayout smartCharge_cl = root.findViewById(R.id.smartCharge_cl);
        ConstraintLayout deepClean_cl = root.findViewById(R.id.deepClean_cl);
        ConstraintLayout speedTest_cl = root.findViewById(R.id.speedTest_cl);
        ConstraintLayout repair_cl = root.findViewById(R.id.repair_cl);

//        b_optimize.setOnClickListener(this);

        @SuppressLint("DefaultLocale") String temp1 = String.format("%d", (int) utils.cpuTemperature());
        tv_num.setText(temp1);

        ivAppCleanup.setOnClickListener(this);
        ivPowerSaving.setOnClickListener(this);
        ivStatusSaver.setOnClickListener(this);
        ivJunkFile.setOnClickListener(this);
        ivCpuCooler.setOnClickListener(this);
        ivBoostPhone.setOnClickListener(this);

        photoVideo_cl.setOnClickListener(this);
        cleanWhatsApp_cl.setOnClickListener(this);
        smartCharge_cl.setOnClickListener(this);
        deepClean_cl.setOnClickListener(this);
        speedTest_cl.setOnClickListener(this);
        repair_cl.setOnClickListener(this);

        ramAndStorageFun();


    }


    private void ramAndStorageFun() {
//        TextView ramPercent_tv = root.findViewById(R.id.ramPercent_tv);
//        TextView storageUsagePercent_tv = root.findViewById(R.id.storageUsagePercent_tv);

//for Ram
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        float totalRam1 = memoryInfo.totalMem;
        float freeRam1 = memoryInfo.availMem;
        float usedRam = totalRam1 - freeRam1;
//        ramPercent_tv.setText( String.format( "%.1f",utils.getPercentage( totalRam1,usedRam ) )+"%" );
        cpbRam.setAdProgress((int) utils.getPercentage(totalRam1, usedRam));
        pbRam.setProgress((int) utils.getPercentage(totalRam1, usedRam));
        tvRamInfo.setText(String.format(utils.getCalculatedDataSize(usedRam) + "/" + utils.getCalculatedDataSize(totalRam1)));

        // animation start
        ValueAnimator ramAnimator = ValueAnimator.ofInt(0, (int) utils.getPercentage(totalRam1, usedRam));
        ramAnimator.setInterpolator(new LinearInterpolator());
        ramAnimator.setStartDelay(0);
        ramAnimator.setDuration(1_500);
        ramAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                cpbRam.setProgress(value);
                pbRam.setProgress(value);
            }
        });
        ramAnimator.start();
        // animation end

        //for Storage
        float totalStorageBytes, availableStorageBytes, usedStorageBytes;

        totalStorageBytes = utils.getTotalStorage();
        availableStorageBytes = utils.getAvailableStorage();

        usedStorageBytes = totalStorageBytes - availableStorageBytes;

        cpbStorage.setAdProgress((int) utils.getPercentage(totalStorageBytes, usedStorageBytes));
        pbStorage.setProgress((int) utils.getPercentage(totalStorageBytes, usedStorageBytes));
        tvStorageInfo.setText(utils.getCalculatedDataSize(usedStorageBytes) + "/" + utils.getCalculatedDataSize(totalStorageBytes));

        // animation start
        ValueAnimator storageAnimator = ValueAnimator.ofInt(0, (int) utils.getPercentage(totalStorageBytes, usedStorageBytes));
        storageAnimator.setInterpolator(new LinearInterpolator());
        storageAnimator.setStartDelay(0);
        storageAnimator.setDuration(1_500);
        storageAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                cpbStorage.setProgress(value);
                pbStorage.setProgress(value);
            }
        });
        storageAnimator.start();
        // animation end

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.photoVideo_cl:
                sNewActivityAds(new RepairAct());
                break;
            case R.id.repair_cl:
                sNewActivityAds(new RepairAct());

                break;
            case R.id.cleanWhatsApp_cl:
                if (permissions.permission()) {
                    sNewActivityAds(new CleanWhatsAppAct());
                } else
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_junk_file:
                if (permissions.permission()) {
                    sNewActivityAds(new JunkFilesAct());

                } else
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_cpu_cooler:

                if (permissions.permission()) {
                    sNewActivityAds(new CpuCooler());

                } else
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_boost_phone:
                if (permissions.permission()) {
                    sNewActivityAds(new PhoneBoostAct());
                } else
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_power_saving:

                if (permissions.permission()) {
                    sNewActivityAds(new BatterySavingAct());
                } else {
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.iv_app_cleanup:

                if (permissions.permission()) {
                    sNewActivityAds(new UnInstallAppAct());

                } else {
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_status_saver:

                if (permissions.permission()) {
                    sNewActivityAds(new WhatsAppStatusAct());
                } else {
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.smartCharge_cl:

                if (permissions.permission()) {
                    sNewActivityAds(new SmartChargingAct());
                } else {
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.deepClean_cl:
                if (permissions.permission()) {
                    sNewActivityAds(new DeepCleanAct());
                } else {
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.speedTest_cl:
                if (permissions.permission()) {
                    sNewActivityAds(new InternetSpeedAct());
                } else {
                    Toast.makeText(getContext(), "Permission is not granted ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}