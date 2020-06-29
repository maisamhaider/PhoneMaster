package com.example.phonemaster.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.CommonAdapter;
import com.example.phonemaster.async.WhatsAppCommonTask;

import java.io.File;
import java.util.List;

public abstract class WhatsAppBaseActivity extends AppCompatActivity {

    CommonAdapter commonAdapter;
    RecyclerView rvCleanWhatsApp;
    String type;
    Group group;
    TextView noDatatv;


    public void alertDialog() {
        View view = getLayoutInflater().inflate(R.layout.are_you_sure_to_delete_dialog_layout, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                cleanData();
                dialog.dismiss();
            }
        });
    }

    public void cleanData() {
        File file;
        List<String> imagePathList = commonAdapter.getList();
        for (int i = 0; i < imagePathList.size(); i++) {
            try {
                try {
                    file = new File(imagePathList.get(i));
                    if (file.delete()) {
                        Log.i("TAG", "Delete  ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        WhatsAppCommonTask whatsAppCommonTask = new WhatsAppCommonTask(this, commonAdapter, rvCleanWhatsApp, type);
        whatsAppCommonTask.execute();
        commonAdapter.notifyDataSetChanged();
    }
    public void toggleVisibility(boolean isRvVisible) {
        if (isRvVisible){
            group.setVisibility(View.VISIBLE);
            noDatatv.setVisibility(View.GONE);
        }else{
            group.setVisibility(View.GONE);
            noDatatv.setVisibility(View.VISIBLE);
        }
    }


}
