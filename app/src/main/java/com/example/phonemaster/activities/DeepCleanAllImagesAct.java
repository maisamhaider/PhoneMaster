package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanImagesAdapter;
import com.example.phonemaster.async.DeepCleanImagesTask;
import com.example.phonemaster.utils.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
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