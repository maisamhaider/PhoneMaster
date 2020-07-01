package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phonemaster.R;

import java.io.IOException;
import java.io.InputStream;

public class ProcessorDetailAct extends AppCompatActivity {

    TextView textView ;
    ProcessBuilder processBuilder;
    String Holder = "";
    String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
    InputStream inputStream;
    Process process ;
    byte[] byteArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        textView = findViewById(R.id.textView);

        byteArray = new byte[1024];
        try{
            processBuilder = new ProcessBuilder(DATA);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while(inputStream.read(byteArray) != -1){
                Holder = Holder + new String(byteArray);
            }
            inputStream.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        textView.setText(Holder);
    }
}