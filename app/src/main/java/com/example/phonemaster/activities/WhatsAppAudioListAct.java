package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.CommonAdapter;
import com.example.phonemaster.adapters.DeepCleanAudioAdapter;
import com.example.phonemaster.adapters.DeepCleanDocsAdapter;
import com.example.phonemaster.async.DeepCleanDocsTask;
import com.example.phonemaster.async.WhatsAppCommonTask;
import com.example.phonemaster.models.DeepCleanAudioModel;
import com.example.phonemaster.models.DeepCleanDocsModel;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
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

        RecyclerView whatsAppAudioList_rv = findViewById(R.id.whatsAppAudioList_rv);
        Button whatsAppAudioList_btn = findViewById(R.id.whatsAppAudioList_btn);

        CommonAdapter commonAdapter = new CommonAdapter(this);
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