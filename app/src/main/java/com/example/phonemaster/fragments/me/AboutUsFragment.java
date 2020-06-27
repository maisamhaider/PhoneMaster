package com.example.phonemaster.fragments.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonemaster.BuildConfig;
import com.example.phonemaster.R;
import com.example.phonemaster.activities.MainActivity;

public class AboutUsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        ImageView ivTool = root.findViewById(R.id.iv_s_back);
        ConstraintLayout shareCl = root.findViewById(R.id.share_cl);
        ConstraintLayout rateUsCl = root.findViewById(R.id.rateUs_cl);
        ConstraintLayout aboutUsCl = root.findViewById(R.id.aboutUs_cl);

        ivTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).onBackPressed();
            }
        });

        shareCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateUs();
            }
        });
        rateUsCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateUs();
            }
        });

        return root;
    }

    public void rateUs(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}