package com.example.phonemaster.fragments.tools;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.phonemaster.R;
import com.example.phonemaster.activities.BluetoothInfoAct;
import com.example.phonemaster.activities.FeaturesAct;
import com.example.phonemaster.activities.FilesMoverAct;
import com.example.phonemaster.activities.HarassmentFilterAct;
import com.example.phonemaster.activities.DeviceInfoAct;
import com.example.phonemaster.activities.HardwareTest;
import com.example.phonemaster.activities.InternetSpeedAct;
import com.example.phonemaster.activities.MainActivity;
import com.example.phonemaster.activities.ProcessorDetailAct;
import com.example.phonemaster.activities.RootCheckerAct;
import com.example.phonemaster.activities.SensorListAct;
import com.example.phonemaster.activities.UnInstallAppAct;

public class ToolsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tool, container, false);
        ImageView ivTool = root.findViewById(R.id.iv_t_back);
        ConstraintLayout appInstall_cl = root.findViewById(R.id.appInstall_cl);
//        ConstraintLayout fileMover_cl = root.findViewById(R.id.fileMover_cl);
        ConstraintLayout hardwareInfo_cl = root.findViewById(R.id.hardwareInfo_cl);
         ConstraintLayout harassmentFiler_cl = root.findViewById(R.id.harassmentFiler_cl);
        ConstraintLayout rootchecker_cl = root.findViewById(R.id.rootchecker_cl);
        ConstraintLayout sensorlist_cl = root.findViewById(R.id.sensorlist_cl);
        ConstraintLayout processesor_cl = root.findViewById(R.id.processesor_cl);
        ConstraintLayout hrdTest_cl = root.findViewById(R.id.hrdTest_cl);
        ConstraintLayout blueInfo_cl = root.findViewById(R.id.blueInfo_cl);
        ConstraintLayout deviceFeatures_cl = root.findViewById(R.id.deviceFeatures_cl);

        ivTool.setOnClickListener((View.OnClickListener) v -> ((MainActivity) requireActivity()).onBackPressed());
        appInstall_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), UnInstallAppAct.class)));
//        fileMover_cl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), FilesMoverAct.class));
//            }
//        });
        processesor_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), ProcessorDetailAct.class)));
        rootchecker_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), RootCheckerAct.class)));
        sensorlist_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), SensorListAct.class)));
        hardwareInfo_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), DeviceInfoAct.class)));
        blueInfo_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), BluetoothInfoAct.class)));
        hrdTest_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), HardwareTest.class)));
         deviceFeatures_cl.setOnClickListener(v -> startActivity(new Intent(getActivity(), FeaturesAct.class)));
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            harassmentFiler_cl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), HarassmentFilterAct.class));
                }
            });
        } else {
            harassmentFiler_cl.setVisibility(View.GONE);
        }
        return root;
    }
}