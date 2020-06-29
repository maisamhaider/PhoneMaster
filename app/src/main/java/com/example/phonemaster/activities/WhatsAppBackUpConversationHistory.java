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

public class WhatsAppBackUpConversationHistory extends WhatsAppBaseActivity {

    Utils utils;
    File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_up_conversation_history);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);

        rvCleanWhatsApp = findViewById(R.id.whatsAppBUCHList_rv);
        Button whatsAppBUCHList_btn = findViewById(R.id.whatsAppBUCHList_btn);
        type = "BUCH";

        commonAdapter = new CommonAdapter(this, CommonAdapter.BACKUP);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this, commonAdapter, rvCleanWhatsApp, type );
        whatsAppCommonTask.execute();

        whatsAppBUCHList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
    }



}