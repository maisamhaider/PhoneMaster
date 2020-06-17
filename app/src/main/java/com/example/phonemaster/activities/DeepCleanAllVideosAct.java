package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanVideosAdapter;
import com.example.phonemaster.async.DeepCleanVideosTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllVideosAct extends AppCompatActivity {

    DeepCleanVideosAdapter deepCleanImagesAdapter;
    DeepCleanVideosTask deepCleanImagesTask;

    Utils utils;
     File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_videos_atc);

        utils = new Utils(this);
        RecyclerView deepCleanAllVideos_rv;
        deepCleanAllVideos_rv = findViewById(R.id.DeepCleanAllVideos_rv);
        Button deepCleanVideos_btn = findViewById(R.id.deepCleanVideos_btn);

        deepCleanImagesAdapter = new DeepCleanVideosAdapter(this);
        deepCleanImagesTask = new DeepCleanVideosTask(this, deepCleanImagesAdapter, deepCleanAllVideos_rv);
        deepCleanImagesTask.execute();

        deepCleanVideos_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> imagePathList = deepCleanImagesAdapter.getList();
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