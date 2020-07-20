package com.cleaner.booster.phone.repairer.app.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.adapters.CommonAdapter;
import com.cleaner.booster.phone.repairer.app.async.WhatsAppCommonTask;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

import java.io.File;

public class WhatsAppAudioListAct extends WhatsAppBaseActivity {

    String[] fileNames;
    Utils utils;
    private File file;
    private Button whatsAppAudioList_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_data_list);
        utils = new Utils(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rvCleanWhatsApp = findViewById(R.id.whatsApp_rv);
        whatsAppAudioList_btn = findViewById(R.id.clean_btn);
        group = findViewById(R.id.group);
        noDatatv = findViewById(R.id.no_data_tv);

        type = "audios";
        commonAdapter = new CommonAdapter(this, CommonAdapter.AUDIO);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this, commonAdapter, rvCleanWhatsApp, type);
        whatsAppCommonTask.execute();

        whatsAppAudioList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
    }
}
