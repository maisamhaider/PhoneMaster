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

public class WhatsAppAudioListAct extends AppCompatActivity {

    String[] fileNames;
    Utils utils;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_audio_list);
        utils = new Utils(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RecyclerView whatsAppAudioList_rv = findViewById(R.id.whatsAppAudioList_rv);
        Button whatsAppAudioList_btn = findViewById(R.id.whatsAppAudioList_btn);

        CommonAdapter commonAdapter = new CommonAdapter(this,CommonAdapter.AUDIO);
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this,commonAdapter,whatsAppAudioList_rv,"audios");
        whatsAppCommonTask.execute();

        whatsAppAudioList_btn.setOnClickListener(new View.OnClickListener() {
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