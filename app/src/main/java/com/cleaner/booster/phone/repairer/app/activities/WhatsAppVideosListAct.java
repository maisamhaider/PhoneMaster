package com.cleaner.booster.phone.repairer.app.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.adapters.CommonAdapter;
import com.cleaner.booster.phone.repairer.app.async.WhatsAppCommonTask;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

public class WhatsAppVideosListAct extends WhatsAppBaseActivity {

    String[] fileNames;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_data_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);
        rvCleanWhatsApp = findViewById(R.id.whatsApp_rv);
        Button whatsAppVideosList_btn = findViewById(R.id.clean_btn);
        group = findViewById(R.id.group);
        noDatatv = findViewById(R.id.no_data_tv);

        type = "videos";
        CommonAdapter commonAdapter = new CommonAdapter(this,CommonAdapter.VIDEO);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this,commonAdapter,rvCleanWhatsApp,type);
        whatsAppCommonTask.execute();

        whatsAppVideosList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              alertDialog();
            }
        });
      }
}