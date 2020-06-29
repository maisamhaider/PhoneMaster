package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.CommonAdapter;
import com.example.phonemaster.async.WhatsAppCommonTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class WhatsAppImagesListAct extends WhatsAppBaseActivity {

    String[] fileNames;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_images_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);
        rvCleanWhatsApp = findViewById(R.id.whatsAppImagesList_rv);
        Button whatsAppImagesList_btn = findViewById(R.id.whatsAppImagesList_btn);
        type = "images";
        commonAdapter = new CommonAdapter(this, CommonAdapter.IMAGE);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this, commonAdapter, rvCleanWhatsApp, type);
        whatsAppCommonTask.execute();

        whatsAppImagesList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });


    }
}