package com.cleaner.booster.phone.repairer.app.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.adapters.CommonAdapter;
import com.cleaner.booster.phone.repairer.app.async.WhatsAppCommonTask;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

public class WhatsAppDocumentsListAct extends WhatsAppBaseActivity {
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_data_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);
        //list of selected folder images


         rvCleanWhatsApp = findViewById(R.id.whatsApp_rv);
        Button whatsAppDocList_btn = findViewById(R.id.clean_btn);
        group = findViewById(R.id.group);
        noDatatv = findViewById(R.id.no_data_tv);

        type = "doc";
        commonAdapter = new CommonAdapter(this,CommonAdapter.DOCUMENT);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this,commonAdapter,rvCleanWhatsApp,type);
        whatsAppCommonTask.execute();

        whatsAppDocList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alertDialog();
            }
        });
    }
}
