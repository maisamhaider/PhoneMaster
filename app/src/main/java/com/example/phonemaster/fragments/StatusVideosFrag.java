package com.example.phonemaster.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.WhatsAppStatusAdapter;
import com.example.phonemaster.models.CommonModel;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.List;


public class StatusVideosFrag extends Fragment {



    public StatusVideosFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_status_videos, container, false);

        Utils utils = new Utils(getContext());

        RecyclerView statusVideos_rv = view.findViewById(R.id.statusVideos_rv);
        LinearLayout saveStatusVideos_ll = view.findViewById(R.id.saveStatusVideos_ll);

        List<CommonModel> list  ;
        File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/.Statuses");
        list = utils.getListFiles(file1,"videos");

        statusVideos_rv.setLayoutManager(new GridLayoutManager(getContext(),2));
        WhatsAppStatusAdapter statusAdapter = new WhatsAppStatusAdapter(getContext(),list);
        statusVideos_rv.setAdapter(statusAdapter);
        statusAdapter.notifyDataSetChanged();

        saveStatusVideos_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> data = statusAdapter.getSendList();
                Permissions p = new Permissions(getContext());
                if (p.permission()) {
                    if (data.size() != 0) {
                        for (int i = 0; i < data.size(); i++) {
                            utils.copyFileOrDirectory(data.get(i));
                        }
                    }
                }
            }
        });
        return view;
    }
}