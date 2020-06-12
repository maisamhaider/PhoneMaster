package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.WhatsAppStatusAdapter;
import com.example.phonemaster.utils.Utils;

import java.io.File;

public class WhatsAppStatusAct extends AppCompatActivity {
    Utils utils;
    RecyclerView whatsAppStatus_rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_status);
        utils = new Utils(this);

        whatsAppStatus_rv = findViewById(R.id.whatsAppStatus_rv);

        File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/.Statuses");


        Log.i("listSize", String.valueOf(utils.getListFiles(file1).size()));
        whatsAppStatus_rv.setLayoutManager(new GridLayoutManager(this, 3));

        WhatsAppStatusAdapter appStatusAdapter = new WhatsAppStatusAdapter(this, utils.getListFiles(file1));
        whatsAppStatus_rv.setAdapter(appStatusAdapter);
        appStatusAdapter.notifyDataSetChanged();


    }
}