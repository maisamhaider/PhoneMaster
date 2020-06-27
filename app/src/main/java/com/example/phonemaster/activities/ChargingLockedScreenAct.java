package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.utils.StorageUtils;
import com.example.phonemaster.utils.Utils;

import java.util.List;

public class ChargingLockedScreenAct extends AppCompatActivity {

    private ProgressBar pbBattery;
    private TextView tvCharging,tvPercentage;
    private View vHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_locked_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Utils utils = new Utils(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            this.setShowWhenLocked(true);
        }

        ImageView ivJunkFile = findViewById(R.id.iv_junk_file);
        ImageView ivCpuCooler = findViewById(R.id.iv_cpu_cooler);
        ImageView ivBoostPhone = findViewById(R.id.iv_boost_phone);
        tvPercentage = findViewById(R.id.tv_percentage);
        tvCharging = findViewById(R.id.tv_charging);
        pbBattery = findViewById(R.id.pb_battery);
        vHead = findViewById(R.id.v_head);
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        StorageUtils storageUtils = new StorageUtils();
        String dirPath = String.valueOf(Environment.getExternalStorageDirectory());

        ivJunkFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storageUtils.deleteCache(getApplicationContext());
                storageUtils.deleteEmptyFolder(dirPath);
                List<DeepCleanPackagesModel> pkg = utils.getAllPackages(dirPath);

                if (pkg.size() != 0) {
                    for (int i = 0; i < pkg.size(); i++) {
                        pkg.get(i).getPkgPath();
                    }
                }
            }
        });
        ivCpuCooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < utils.getActiveApps().size(); i++) {
                    am.killBackgroundProcesses(utils.getActiveApps().get(i));
                }
            }
        });

        ivBoostPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < utils.getActiveApps().size(); i++) {
                    am.killBackgroundProcesses(utils.getActiveApps().get(i));
                }
            }
        });

        vHead.setBackground(getResources().getDrawable(R.drawable.s_bg_head));
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        startAnimation(60);
    }

    private void startAnimation(int setLevel) {
        ValueAnimator animator = ValueAnimator.ofInt(0, setLevel);
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(0);
        animator.setDuration(2_000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                pbBattery.setProgress(value);
                String bValue = value+"%";
                tvPercentage.setText(bValue);
                if (value == 100) {
                    vHead.setBackground(getResources().getDrawable(R.drawable.bg_head));
                }
            }
        });
        animator.start();
    }


    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int deviceBatteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int batterySource = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            int batteryVoltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            if (batterySource == 0) {
                tvCharging.setVisibility(View.GONE);
            } else {
                tvCharging.setVisibility(View.VISIBLE);
            }
            startAnimation(level);
        }
    };

}