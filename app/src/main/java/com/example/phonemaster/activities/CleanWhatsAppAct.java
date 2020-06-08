package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.phonemaster.R;

public class CleanWhatsAppAct extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_whats_app);
        ConstraintLayout audio_CL = findViewById(R.id.audio_CL);
        ConstraintLayout images_CL = findViewById(R.id.images_CL);
        ConstraintLayout back_up_conversation_history_CL =findViewById(R.id.back_up_conversation_history_CL);
        ConstraintLayout received_file_CL =findViewById(R.id.back_up_conversation_history_CL);


        back_up_conversation_history_CL.setOnClickListener(this);
        images_CL.setOnClickListener(this);
        audio_CL.setOnClickListener(this);
        received_file_CL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_up_conversation_history_CL:
                Intent intent1 = new Intent(this,WhatsAppBackUpConversationHistory.class);
                startActivity(intent1);
                break;

            case R.id.images_CL:
                Intent intent2 = new Intent(this,WhatsAppImagesListAct.class);
                startActivity(intent2);
                break;
            case R.id.audio_CL:
                Intent intent3 = new Intent(this,WhatsAppAudioListAct.class);
                startActivity(intent3);
                break;
            case R.id.received_file_CL:
                Intent intent4 = new Intent(this,WhatsAppAudioListAct.class);
                startActivity(intent4);
                break;
        }
    }
}