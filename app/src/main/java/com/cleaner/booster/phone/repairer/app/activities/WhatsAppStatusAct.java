package com.cleaner.booster.phone.repairer.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.fragments.StatusImagesFrag;
import com.cleaner.booster.phone.repairer.app.fragments.StatusSavedFrag;
import com.cleaner.booster.phone.repairer.app.fragments.StatusVideosFrag;
import com.cleaner.booster.phone.repairer.app.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class WhatsAppStatusAct extends AppCompatActivity {
    Utils utils;
    Fragment mFragment;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_status);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);

        TabLayout tabLayout = findViewById(R.id.status_tl);
        ImageView statusSaverMain_iv = findViewById(R.id.statusSaverMain_iv);
        TextView amountOfStatusImages_tv = findViewById(R.id.amountOfStatusImages_tv);

        StatusImagesFrag statusImagesFrag = new StatusImagesFrag();
        loadmyfrag(statusImagesFrag);

        File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/.Statuses");
        amountOfStatusImages_tv.setText(utils.getListFiles(file1, "images").size() + " images status found");
        statusSaverMain_iv.setImageResource(R.drawable.ic_status_saver_image_header);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        StatusImagesFrag statusImagesFrag = new StatusImagesFrag();
                        loadmyfrag(statusImagesFrag);
                        amountOfStatusImages_tv.setText(utils.getListFiles(file1, "images").size() + " images status found");
                        statusSaverMain_iv.setImageResource(R.drawable.ic_status_saver_image_header);
                        break;

                    case 1:
                        StatusVideosFrag statusVideosFrag = new StatusVideosFrag();
                        loadmyfrag(statusVideosFrag);
                        File file2 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/.Statuses");
                        amountOfStatusImages_tv.setText(utils.getListFiles(file2, "images").size() + " videos status found");
                        statusSaverMain_iv.setImageResource(R.drawable.ic_status_saver_video_header);
                        break;

                    case 2:
                        StatusSavedFrag statusSavedFrag = new StatusSavedFrag();
                        loadmyfrag(statusSavedFrag);
                        amountOfStatusImages_tv.setText("Status Saved");
                        statusSaverMain_iv.setImageResource(R.drawable.ic_status_saver_saved_header);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void loadmyfrag(Fragment fragment) {
        this.mFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.whatsAppStatusFragContainer_fl, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}