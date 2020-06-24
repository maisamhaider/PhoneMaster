package com.example.phonemaster.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonemaster.R;
import com.example.phonemaster.adapters.WhatsAppStatusAdapter;
import com.example.phonemaster.models.CommonModel;
import com.example.phonemaster.permission.Permissions;
import com.example.phonemaster.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StatusSavedFrag extends Fragment {


    public StatusSavedFrag() {
        // Required empty public constructor
    }

    public static StatusSavedFrag newInstance() {
        return new StatusSavedFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status_saved, container, false);

        Utils utils = new Utils(getContext());

        RecyclerView statusSaved_rv = view.findViewById(R.id.statusSaved_rv);


        File file1 = new File(Environment.getExternalStorageDirectory().getPath() + "/Phone_Master_Status");
        List<CommonModel> list = utils.getListFiles(file1);

        statusSaved_rv.setLayoutManager(new GridLayoutManager(getContext(),2));
        WhatsAppStatusAdapter statusAdapter = new WhatsAppStatusAdapter(getContext(),list,true);
        statusSaved_rv.setAdapter(statusAdapter);
        statusAdapter.notifyDataSetChanged();



        return view;
    }
}