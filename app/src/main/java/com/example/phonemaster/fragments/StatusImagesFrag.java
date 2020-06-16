package com.example.phonemaster.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.WhatsAppStatusAdapter;
import com.example.phonemaster.models.CommonModel;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class StatusImagesFrag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status_images, container, false);
        Utils utils = new Utils(getContext());

        RecyclerView statusImages_rv = view.findViewById(R.id.statusImages_rv);
        LinearLayout saveStatusImages_ll = view.findViewById(R.id.saveStatusImages_ll);


        File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/WhatsApp/Media/.Statuses");
        List<CommonModel> list = utils.getListFiles(file1,"images");

        statusImages_rv.setLayoutManager(new GridLayoutManager(getContext(),2));
        WhatsAppStatusAdapter statusAdapter = new WhatsAppStatusAdapter(getContext(),list,false);
        statusImages_rv.setAdapter(statusAdapter);
        statusAdapter.notifyDataSetChanged();

        saveStatusImages_ll.setOnClickListener(new View.OnClickListener() {
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