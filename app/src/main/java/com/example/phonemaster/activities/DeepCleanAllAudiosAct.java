package com.example.phonemaster.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanAudioAdapter;
import com.example.phonemaster.async.DeepCleanAudiosTask;
import com.example.phonemaster.async.FileMoverTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllAudiosAct extends AppCompatActivity {

    DeepCleanAudioAdapter deepCleanAudioAdapter;
    DeepCleanAudiosTask deepCleanAudiosTask;
    Utils utils;
    File file;
    boolean isSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_audios);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);
        RecyclerView deepCleanAllAudio_rv;
        deepCleanAllAudio_rv = findViewById(R.id.deepCleanAllAudio_rv);
        LinearLayout deepCleanAudiosClean_ll = findViewById(R.id.deepCleanAudiosClean_ll);
        TextView deepCleanAudiosCleanBtn_tv = findViewById(R.id.deepCleanAudiosCleanBtn_tv);

        isSend= getIntent().getBooleanExtra("isSend",false);

        deepCleanAudioAdapter = new DeepCleanAudioAdapter(this);
        deepCleanAudiosTask = new DeepCleanAudiosTask(this, deepCleanAudioAdapter, deepCleanAllAudio_rv);
        deepCleanAudiosTask.execute();

        if (isSend)
        {
            deepCleanAudiosCleanBtn_tv.setText("MOVE");
        }
        deepCleanAudiosClean_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> pathList = deepCleanAudioAdapter.getList();
                if (isSend)
                {
                    FileMoverTask fileMoverTask = new FileMoverTask(getApplicationContext(),pathList,"Audios");
                    fileMoverTask.execute();

                }else {
                View view = getLayoutInflater().inflate(R.layout.are_you_sure_to_delete_dialog_layout, null, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(DeepCleanAllAudiosAct.this);
                LinearLayout no_ll = view.findViewById(R.id.no_ll);
                LinearLayout yes_ll = view.findViewById(R.id.yes_ll);

                builder.setView(view).setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                no_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                yes_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < pathList.size(); i++) {
                            try {
                                file = new File(pathList.get(i));
                                utils.scanaddedFile(pathList.get(i));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        dialog.dismiss();
                    }
                });
            }}
        });
    }
}