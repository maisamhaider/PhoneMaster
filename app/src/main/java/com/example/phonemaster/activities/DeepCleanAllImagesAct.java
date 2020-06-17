package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanImagesAdapter;
import com.example.phonemaster.async.DeepCleanImagesTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllImagesAct extends AppCompatActivity {


    DeepCleanImagesTask deepCleanImagesTask;
    DeepCleanImagesAdapter deepCleanImagesAdapter;
    File file;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_images);
        utils = new Utils(this);
        RecyclerView DeepCleanAllImages_rv;
        DeepCleanAllImages_rv = findViewById(R.id.DeepCleanAllImages_rv);
        Button deepCleanImages_btn = findViewById(R.id.deepCleanImages_btn);

        deepCleanImagesAdapter = new DeepCleanImagesAdapter(this);
        deepCleanImagesTask = new DeepCleanImagesTask(this, deepCleanImagesAdapter, DeepCleanAllImages_rv);
        deepCleanImagesTask.execute();

        deepCleanImages_btn.setOnClickListener(new View.OnClickListener() {
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