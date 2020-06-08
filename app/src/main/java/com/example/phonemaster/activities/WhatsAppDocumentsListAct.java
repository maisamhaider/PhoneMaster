package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

import java.io.File;

public class WhatsAppDocumentsListAct extends AppCompatActivity {
    String[] fileNames;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_documents_list);
        utils = new Utils(this);
        File file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Media/WhatsApp Documents");
        //list of selected folder images
        utils.getListFiles(file1,"doc");
    }
}