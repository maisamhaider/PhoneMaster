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

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanImagesAdapter;
import com.example.phonemaster.async.DeepCleanImagesTask;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllImagesAct extends AppCompatActivity {


    DeepCleanImagesTask deepCleanImagesTask;
    DeepCleanImagesAdapter deepCleanImagesAdapter;
    File file;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_images);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        utils = new Utils(this);
        RecyclerView DeepCleanAllImages_rv;
        DeepCleanAllImages_rv = findViewById(R.id.DeepCleanAllImages_rv);
        LinearLayout deepCleanImagesClean_ll = findViewById(R.id.deepCleanImagesClean_ll);

        deepCleanImagesAdapter = new DeepCleanImagesAdapter(this);
        deepCleanImagesTask = new DeepCleanImagesTask(this, deepCleanImagesAdapter, DeepCleanAllImages_rv);
        deepCleanImagesTask.execute();

        deepCleanImagesClean_ll.setOnClickListener(v -> {
            List<String> pathList = deepCleanImagesAdapter.getList();

            View view = getLayoutInflater().inflate(R.layout.are_you_sure_to_delete_dialog_layout,null,false);
            AlertDialog.Builder builder =  new AlertDialog.Builder(DeepCleanAllImagesAct.this);
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
        });


    }

}