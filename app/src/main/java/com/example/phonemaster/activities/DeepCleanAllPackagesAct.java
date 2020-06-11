package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanDocsAdapter;
import com.example.phonemaster.adapters.DeepCleanPackagesAdapter;
import com.example.phonemaster.async.DeepCleanDocsTask;
import com.example.phonemaster.async.DeepCleanPkgsTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllPackagesAct extends AppCompatActivity {

    DeepCleanPackagesAdapter deepCleanPackagesAdapter;
    DeepCleanPkgsTask deepCleanPkgsTask;

    Utils utils;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_packages);

        utils = new Utils(this);
        RecyclerView deepCleanAllPkgs_rv;
        deepCleanAllPkgs_rv = findViewById(R.id.deepCleanAllPkgs_rv);
        Button deepCleanPkgs_btn = findViewById(R.id.deepCleanPkgs_btn);

        deepCleanPackagesAdapter = new DeepCleanPackagesAdapter(this);
        deepCleanPkgsTask = new DeepCleanPkgsTask(this, deepCleanPackagesAdapter, deepCleanAllPkgs_rv);
        deepCleanPkgsTask.execute();

        deepCleanPkgs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> imagePathList = deepCleanPackagesAdapter.getList();
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