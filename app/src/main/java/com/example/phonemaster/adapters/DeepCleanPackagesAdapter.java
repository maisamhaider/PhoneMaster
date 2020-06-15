package com.example.phonemaster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanDocsModel;
import com.example.phonemaster.models.DeepCleanPackagesModel;

import java.util.ArrayList;
import java.util.List;

public class DeepCleanPackagesAdapter extends RecyclerView.Adapter<DeepCleanPackagesAdapter.WhatsAppStatusHolder> {
    Context context;
    List<DeepCleanPackagesModel> fileList;
    List<String> list;

    public DeepCleanPackagesAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setFileList(List<DeepCleanPackagesModel> fileList) {
        this.fileList = fileList;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WhatsAppStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deep_clean_pkgs_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String pkgString = fileList.get(position).getPkgPath();
        final String pkgName = fileList.get(position).getPkgName();


        holder.deepCleanPkgName_tv.setText(pkgName);


        holder.selectPkg_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    list.add(pkgString);
                }
                else
                {
                    list.remove(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView deepCleanPgksRv_iv;
        CheckBox selectPkg_cb;
        TextView deepCleanPkgName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanPgksRv_iv = itemView.findViewById(R.id.deepCleanPgksRv_iv);
            selectPkg_cb = itemView.findViewById(R.id.selectPkg_cb);
            deepCleanPkgName_tv = itemView.findViewById(R.id.deepCleanPkgName_tv);

        }
    }
}
