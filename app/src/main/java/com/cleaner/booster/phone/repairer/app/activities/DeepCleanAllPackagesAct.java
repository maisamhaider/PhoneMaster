package com.cleaner.booster.phone.repairer.app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.adapters.DeepCleanPackagesAdapter;
import com.cleaner.booster.phone.repairer.app.async.DeepCleanPkgsTask;
import com.cleaner.booster.phone.repairer.app.async.FileMoverTask;
import com.cleaner.booster.phone.repairer.app.utils.Utils;

import java.io.File;
import java.util.List;

public class DeepCleanAllPackagesAct extends AppCompatActivity {

    DeepCleanPackagesAdapter deepCleanPackagesAdapter;
    DeepCleanPkgsTask deepCleanPkgsTask;

    Utils utils;
    File file;

    boolean isSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean_all_packages);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        utils = new Utils(this);

        RecyclerView deepCleanAllPkgs_rv = findViewById(R.id.deepCleanAllPkgs_rv);
        LinearLayout deepCleanPkgs_ll = findViewById(R.id.deepCleanPkgs_ll);
        TextView deepCleanpkgsBtn_tv = findViewById(R.id.deepCleanpkgsBtn_tv);

        isSend= getIntent().getBooleanExtra("isSend",false);


        deepCleanPackagesAdapter = new DeepCleanPackagesAdapter(this);
        deepCleanPkgsTask = new DeepCleanPkgsTask(this, deepCleanPackagesAdapter, deepCleanAllPkgs_rv);
        deepCleanPkgsTask.execute();

        if (isSend)
        {
            deepCleanpkgsBtn_tv.setText("MOVE");
        }
        deepCleanPkgs_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> pathList = deepCleanPackagesAdapter.getList();

                if (isSend)
                {
                    FileMoverTask fileMoverTask = new FileMoverTask(getApplicationContext(),pathList,"Packages");
                    fileMoverTask.execute();

                }else {

                    View view = getLayoutInflater().inflate(R.layout.are_you_sure_to_delete_dialog_layout, null, false);
                    AlertDialog.Builder builder = new AlertDialog.Builder(DeepCleanAllPackagesAct.this);
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
//                        utils.scanaddedFile(imagePathList.get(i));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            dialog.dismiss();
                            finish();

                        }
                    });
                }
            }
        });
    }
}