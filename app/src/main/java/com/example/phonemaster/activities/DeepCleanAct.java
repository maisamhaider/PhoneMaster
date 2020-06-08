package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.DeepCleanVideosAdapter;
import com.example.phonemaster.permission.Permissions;

public class DeepCleanAct extends AppCompatActivity {

    private static final int PICK_IMAGES = 1;
    private Permissions permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_clean);
        permissions = new Permissions(this);
         ImageView deepCleanImage_iv1,deepCleanImage_iv2,deepCleanImage_iv3,deepCleanImage_iv4;
        deepCleanImage_iv1 = findViewById(R.id.deepCleanImage_iv1);
        deepCleanImage_iv2 = findViewById(R.id.deepCleanImage_iv2);
        deepCleanImage_iv3 = findViewById(R.id.deepCleanImage_iv3);
        deepCleanImage_iv4 = findViewById(R.id.deepCleanImage_iv4);

        TextView deepCleanImagesSize_tv,deepCleanVideosSize_tv;
        deepCleanImagesSize_tv = findViewById(R.id.deepCleanImagesSize_tv);
        deepCleanVideosSize_tv = findViewById(R.id.deepCleanVideosSize_tv);

        deepCleanImagesSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this,DeepCleanAllImagesAct.class);
                    startActivity(intent);
                }
            }
        });
        deepCleanVideosSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissions.permission())
                {
                    Intent intent = new Intent(DeepCleanAct.this, DeepCleanAllVideosAct.class);
                    startActivity(intent);
                }
            }
        });
    }

}