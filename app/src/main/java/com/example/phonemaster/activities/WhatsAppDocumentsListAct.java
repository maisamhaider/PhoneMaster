package com.example.phonemaster.activities;

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

public class WhatsAppDocumentsListAct extends WhatsAppBaseActivity {
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_documents_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);
        //list of selected folder images


         rvCleanWhatsApp = findViewById(R.id.whatsAppDocList_rv);
        Button whatsAppDocList_btn = findViewById(R.id.whatsAppDocList_btn);
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