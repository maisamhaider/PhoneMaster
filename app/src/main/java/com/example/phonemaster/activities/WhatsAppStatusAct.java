package com.example.phonemaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.WhatsAppStatusAdapter;
import com.example.phonemaster.fragments.MainFrag;
import com.example.phonemaster.fragments.StatusImagesFrag;
import com.example.phonemaster.fragments.StatusSavedFrag;
import com.example.phonemaster.fragments.StatusVideosFrag;
import com.example.phonemaster.utils.Utils;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.File;

public class WhatsAppStatusAct extends AppCompatActivity {
    Utils utils;
    RecyclerView whatsAppStatus_rv;

    Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app_status);
        utils = new Utils(this);
        MainFrag mf = new MainFrag();
        loadmyfrag( mf );

        TabLayout tabLayout = findViewById(R.id.status_tl);
        TabItem images_ti = findViewById(R.id.ti_image);
        TabItem videos_ti = findViewById(R.id.ti_video);
        TabItem saved_ti = findViewById(R.id.ti_saved);

        StatusImagesFrag statusImagesFrag = new StatusImagesFrag();
        loadmyfrag(statusImagesFrag);

         tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                        StatusImagesFrag statusImagesFrag = new StatusImagesFrag();
                loadmyfrag(statusImagesFrag);
                         break;
                    case 1:
                        StatusVideosFrag statusVideosFrag = new StatusVideosFrag();
                loadmyfrag(statusVideosFrag);

                        break;
                    case 2:
                        StatusSavedFrag statusSavedFrag = new StatusSavedFrag();
                loadmyfrag(statusSavedFrag);
                        break;
                }
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });



    }
    public void loadmyfrag(Fragment fragment) {
        this.mFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace( R.id.whatsAppStatusFragContainer_fl, fragment );
        fragmentTransaction.commit();
    }
}