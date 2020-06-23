package com.example.phonemaster.fragments.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonemaster.R;
import com.example.phonemaster.activities.FilesMoverAct;
import com.example.phonemaster.activities.InternetSpeedAct;
import com.example.phonemaster.activities.MainActivity;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tool, container, false);
        ImageView ivTool = root.findViewById(R.id.iv_t_back);
        ConstraintLayout speedTest_cl = root.findViewById(R.id.speedTest_cl);
        ConstraintLayout fileMoverBtn_cl = root.findViewById(R.id.fileMoverBtn_cl);

        ivTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).onBackPressed();
            }
        });


        fileMoverBtn_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FilesMoverAct.class));
            }
        });speedTest_cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InternetSpeedAct.class));
            }
        });

        return root;
    }

}