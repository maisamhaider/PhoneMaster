package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanDocsAdapter;
import com.example.phonemaster.async.DeepCleanDocsTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllDocsAct extends AppCompatActivity {

    DeepCleanDocsAdapter deepCleanDocsAdapter;
    DeepCleanDocsTask deepCleanDocsTask;

    Utils utils;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_docs);

        utils = new Utils(this);
        RecyclerView DeepCleanAllDocs_rv;
        DeepCleanAllDocs_rv = findViewById(R.id.DeepCleanAllDocs_rv);
        Button deepCleanDocs_btn = findViewById(R.id.deepCleanDocs_btn);

        deepCleanDocsAdapter = new DeepCleanDocsAdapter(this);
        deepCleanDocsTask = new DeepCleanDocsTask(this, deepCleanDocsAdapter, DeepCleanAllDocs_rv);
        deepCleanDocsTask.execute();



        deepCleanDocs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> imagePathList = deepCleanDocsAdapter.getList();
                for (int i = 0; i < imagePathList.size(); i++) {
                    try {
                        file = new File(imagePathList.get(i));
                        file.delete();
//                        utils.scanaddedFile(imagePathList.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}