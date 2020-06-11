package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanAudioAdapter;
import com.example.phonemaster.adapters.DeepCleanVideosAdapter;
import com.example.phonemaster.async.DeepCleanAudiosTask;
import com.example.phonemaster.async.DeepCleanVideosTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllAudiosAct extends AppCompatActivity {

    DeepCleanAudioAdapter deepCleanAudioAdapter;
    DeepCleanAudiosTask deepCleanAudiosTask;
    Utils utils;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_audios);
        utils = new Utils(this);
        RecyclerView deepCleanAllAudio_rv;
        deepCleanAllAudio_rv = findViewById(R.id.deepCleanAllAudio_rv);
        Button deepCleanAudio_btn = findViewById(R.id.deepCleanAudio_btn);

        deepCleanAudioAdapter = new DeepCleanAudioAdapter(this);
        deepCleanAudiosTask = new DeepCleanAudiosTask(this, deepCleanAudioAdapter, deepCleanAllAudio_rv);
        deepCleanAudiosTask.execute();

        deepCleanAudio_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> imagePathList = deepCleanAudioAdapter.getList();
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