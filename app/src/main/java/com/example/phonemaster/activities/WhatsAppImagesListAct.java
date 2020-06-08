package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WhatsAppImagesListAct extends AppCompatActivity {

    String[] fileNames;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_images_list);
        utils = new Utils(this);
        File file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Images");
        //list of selected folder images
      utils.getListFiles(file1,"images");


    }
}