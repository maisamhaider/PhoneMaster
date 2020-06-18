package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

public class CleanWhatsAppAct extends AppCompatActivity implements View.OnClickListener {

    private View cleanWpOne, cleanWpTwo;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_whats_app);

        Utils utils = new Utils(this);

        cleanWpOne = findViewById(R.id.clean_wp_one);
        cleanWpTwo = findViewById(R.id.clean_wp_two);

        ConstraintLayout audio_CL = findViewById(R.id.audio_CL);
        ConstraintLayout images_CL = findViewById(R.id.images_CL);
        ConstraintLayout back_up_conversation_history_CL = findViewById(R.id.back_up_conversation_history_CL);
        ConstraintLayout received_file_CL = findViewById(R.id.received_file_CL);
        ConstraintLayout video_CL = findViewById(R.id.video_CL);
        TextView tvTotalSize = findViewById(R.id.tv_total_size);


        TextView back_up_conversation_history_dataSize_Tv, images_dataSize_Tv, audio_dataSize_Tv, Videos_dataSize_Tv, received_file_dataSize_Tv;

        back_up_conversation_history_dataSize_Tv = findViewById(R.id.back_up_conversation_history_dataSize_Tv);
        images_dataSize_Tv = findViewById(R.id.images_dataSize_Tv);
        audio_dataSize_Tv = findViewById(R.id.audio_dataSize_Tv);
        Videos_dataSize_Tv = findViewById(R.id.Videos_dataSize_Tv);
        received_file_dataSize_Tv = findViewById(R.id.received_file_dataSize_Tv);
        pb = findViewById(R.id.progressBar);


        //setting each folder size
        float bUDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Databases");
        float cHDataSize = utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Backups");

        back_up_conversation_history_dataSize_Tv.setText(utils.getCalculatedDataSize(bUDataSize + cHDataSize));
        images_dataSize_Tv.setText(utils.getCalculatedDataSize(
                utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Images")));
        audio_dataSize_Tv.setText(utils.getCalculatedDataSize(
                utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Audio")));
        Videos_dataSize_Tv.setText(utils.getCalculatedDataSize(
                utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Video")));
        received_file_dataSize_Tv.setText(utils.getCalculatedDataSize(
                utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Documents")));

        float totalSize =    bUDataSize + cHDataSize
                                + utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Images")
                                + utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Audio")
                                + utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Video")
                                + utils.getAllSize(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/WhatsApp Documents");

        tvTotalSize.setText(utils.getCalculatedDataSize(totalSize).substring(0,utils.getCalculatedDataSize(totalSize).length()-2));

        startAnimation();

        back_up_conversation_history_CL.setOnClickListener(this);
        images_CL.setOnClickListener(this);
        audio_CL.setOnClickListener(this);
        received_file_CL.setOnClickListener(this);
        video_CL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_up_conversation_history_CL:
                Intent intent1 = new Intent(this, WhatsAppBackUpConversationHistory.class);
                startActivity(intent1);
                break;
            case R.id.images_CL:
                Intent intent2 = new Intent(this, WhatsAppImagesListAct.class);
                startActivity(intent2);
                break;
            case R.id.audio_CL:
                Intent intent3 = new Intent(this, WhatsAppAudioListAct.class);
                startActivity(intent3);
                break;
            case R.id.video_CL:
                Intent intent4 = new Intent(this, WhatsAppVideosListAct.class);
                startActivity(intent4);
                break;
            case R.id.received_file_CL:
                Intent intent5 = new Intent(this, WhatsAppDocumentsListAct.class);
                startActivity(intent5);
                break;
        }
    }

    private void startAnimation() {

        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setInterpolator(new LinearInterpolator());
        animator.setStartDelay(0);
        animator.setDuration(3_000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                pb.setProgress(value);
                cleanWpOne.setVisibility(View.GONE);
                cleanWpTwo.setVisibility(View.VISIBLE);
            }
        });

        animator.start();
    }
}