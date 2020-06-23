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

public class WhatsAppBackUpConversationHistory extends AppCompatActivity {

    Utils utils;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_up_conversation_history);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);

        //list of selected folder images

        RecyclerView whatsAppBUCHList_rv = findViewById(R.id.whatsAppBUCHList_rv);
        Button whatsAppBUCHList_btn = findViewById(R.id.whatsAppBUCHList_btn);

         CommonAdapter  commonAdapter = new CommonAdapter(this,CommonAdapter.BACKUP);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this,commonAdapter,whatsAppBUCHList_rv,"BUCH");
        whatsAppCommonTask.execute();

        whatsAppBUCHList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> imagePathList = commonAdapter.getList();
                for (int i = 0; i < imagePathList.size(); i++) {
                    try {
                        file = new File(imagePathList.get(i));
                        utils.scanaddedFile(imagePathList.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}