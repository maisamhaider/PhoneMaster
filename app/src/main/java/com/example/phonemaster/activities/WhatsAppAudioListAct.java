package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonemaster.R;
 import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WhatsAppAudioListAct extends AppCompatActivity {

    String[] fileNames;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_audio_list);
        utils = new Utils(this);
        File file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Audio");
        //list of selected folder images
         utils.getListFiles(file1,"audios");
        Log.i("size", String.valueOf(utils.getListFiles(file1,"audios").size()));


    }

}