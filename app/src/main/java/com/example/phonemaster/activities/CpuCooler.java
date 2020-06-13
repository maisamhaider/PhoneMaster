package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;
import com.example.phonemaster.R;
import com.example.phonemaster.utils.Utils;

public class CpuCooler extends AppCompatActivity {
    Utils utils ;
    private TextView textView;
//    ActivityManager activitymanager;
//    Context context;
//    List<ActivityManager.RunningAppProcessInfo> RAP ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_cooler);
        textView = findViewById(R.id.qwer);
        utils = new Utils(this);

        for (int i = 0; i < utils.loadProcessInfo().size(); i++)
        {
            textView.setText(textView.getText()  +utils.loadProcessInfo().get(i).process  +"\n\n" );
        }
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        am.killBackgroundProcesses(PACKAGENAME);
    }

}