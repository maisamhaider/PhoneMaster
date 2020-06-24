package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.utils.StorageUtils;
import com.example.phonemaster.utils.Utils;

import java.util.List;

public class ChargingLockedScreenAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_locked_screen);
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Utils utils = new Utils(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            this.setShowWhenLocked(true);
        }

        ImageView ivJunkFile = findViewById(R.id.iv_junk_file);
        ImageView ivCpuCooler = findViewById(R.id.iv_cpu_cooler);
        ImageView ivBoostPhone = findViewById(R.id.iv_boost_phone);
        ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
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
                for (int i = 0; i <utils.getActiveApps().size(); i++) {
                    am.killBackgroundProcesses(utils.getActiveApps().get(i));
                }
            }
        });

        ivBoostPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <utils.getActiveApps().size(); i++) {
                    am.killBackgroundProcesses(utils.getActiveApps().get(i));
                }
            }
        });




    }

}