package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JunkFilesAct extends AppCompatActivity {
    private StorageUtils storageUtils ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junk_files_atc);

        storageUtils = new StorageUtils();

        TextView folderAmount_tv = findViewById(R.id.folderAmount_tv);
        Button junkFileEmptyFolderClear_btn =  findViewById(R.id.junkFileEmptyFolderClear_btn);

        String dirPath = String.valueOf(Environment.getExternalStorageDirectory());
        File file = new File(dirPath);



        junkFileEmptyFolderClear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageUtils.deleteFolder(dirPath);
             }
        });

    }
}