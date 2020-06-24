package com.example.phonemaster.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanDocsAdapter;
import com.example.phonemaster.async.DeepCleanDocsTask;
import com.example.phonemaster.async.FileMoverTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllDocsAct extends AppCompatActivity {

    DeepCleanDocsAdapter deepCleanDocsAdapter;
    DeepCleanDocsTask deepCleanDocsTask;

    Utils utils;
    File file;
    boolean isSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_docs);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        utils = new Utils(this);

        RecyclerView DeepCleanAllDocs_rv;
        DeepCleanAllDocs_rv = findViewById(R.id.DeepCleanAllDocs_rv);
        LinearLayout deepCleanDocs_ll = findViewById(R.id.deepCleanDocs_ll);
        TextView deepCleanDocCleanBtn_tv = findViewById(R.id.deepCleanDocCleanBtn_tv);

        deepCleanDocsAdapter = new DeepCleanDocsAdapter(this);
        deepCleanDocsTask = new DeepCleanDocsTask(this, deepCleanDocsAdapter, DeepCleanAllDocs_rv);
        deepCleanDocsTask.execute();

        isSend= getIntent().getBooleanExtra("isSend",false);
        if (isSend)
        {
            deepCleanDocCleanBtn_tv.setText("MOVE");
        }

        deepCleanDocs_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> pathList = deepCleanDocsAdapter.getList();
                if (isSend)
                {
                    FileMoverTask fileMoverTask = new FileMoverTask(getApplicationContext(),pathList,"Files");
                    fileMoverTask.execute();

                }else {
                    View view = getLayoutInflater().inflate(R.layout.are_you_sure_to_delete_dialog_layout, null, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(DeepCleanAllDocsAct.this);
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
                                    file.delete();
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            dialog.dismiss();
                        }
                    });

                }
            }
        });
    }
}