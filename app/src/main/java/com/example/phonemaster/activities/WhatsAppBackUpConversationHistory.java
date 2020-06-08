package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WhatsAppBackUpConversationHistory extends AppCompatActivity {

     Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_up_conversation_history);
        utils = new Utils(this);
        File file1 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Backups");
        File file12 = new File(Environment.getExternalStorageDirectory().getPath()+"/WhatsApp/Databases");
        //list of selected folder images
        List<File> allImages= new ArrayList<>();
        allImages.addAll(utils.getListFiles(file1,"backup"));
        allImages.addAll(utils.getListFiles(file1,"databases"));
    }
}