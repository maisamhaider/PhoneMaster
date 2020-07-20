package com.cleaner.booster.phone.repairer.app.fragments.tools;

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

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.activities.BluetoothInfoAct;
import com.cleaner.booster.phone.repairer.app.activities.FeaturesAct;
import com.cleaner.booster.phone.repairer.app.activities.FilesMoverAct;
import com.cleaner.booster.phone.repairer.app.activities.HarassmentFilterAct;
import com.cleaner.booster.phone.repairer.app.activities.DeviceInfoAct;
import com.cleaner.booster.phone.repairer.app.activities.HardwareTest;
import com.cleaner.booster.phone.repairer.app.activities.InternetSpeedAct;
import com.cleaner.booster.phone.repairer.app.activities.MainActivity;
import com.cleaner.booster.phone.repairer.app.activities.ProcessorDetailAct;
import com.cleaner.booster.phone.repairer.app.activities.RootCheckerAct;
import com.cleaner.booster.phone.repairer.app.activities.SensorListAct;
import com.cleaner.booster.phone.repairer.app.activities.UnInstallAppAct;

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