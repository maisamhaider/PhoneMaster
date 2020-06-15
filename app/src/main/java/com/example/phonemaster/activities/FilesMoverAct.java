package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phonemaster.R;

public class FilesMoverAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_mover);
        Button selectFile_btn,sendFile_btn;

        selectFile_btn= findViewById(R.id.selectFile_btn);
        sendFile_btn = findViewById(R.id.sendFile_btn);

        selectFile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sendFile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}