package com.cleaner.booster.phone.repairer.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.models.CommonModel;

import java.util.ArrayList;
import java.util.List;

public class DeepCleanPackagesAdapter extends RecyclerView.Adapter<DeepCleanPackagesAdapter.WhatsAppStatusHolder> {
    Context context;
    List<CommonModel> fileList;
    List<String> list;

    public DeepCleanPackagesAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setFileList(List<CommonModel> fileList) {
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

        final String pkgString = fileList.get(position).getPath();
        final String pkgName = fileList.get(position).getName();

        if (list.contains(pkgString)) {
            holder.selectPkg_iv.setImageResource(R.drawable.ic_select);
        } else {
            holder.selectPkg_iv.setImageResource(R.drawable.ic_deselect);
        }
        holder.deepCleanPkgName_tv.setText(pkgName);


        holder.selectPkg_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.contains(pkgString)) {
                    list.add(pkgString);
                    holder.selectPkg_iv.setImageResource(R.drawable.ic_select);
                } else {
                    list.remove(position);
                    holder.selectPkg_iv.setImageResource(R.drawable.ic_deselect);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    static class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView deepCleanPgksRv_iv,selectPkg_iv;
         TextView deepCleanPkgName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanPgksRv_iv = itemView.findViewById(R.id.deepCleanPgksRv_iv);
            selectPkg_iv = itemView.findViewById(R.id.selectPkg_iv);
            deepCleanPkgName_tv = itemView.findViewById(R.id.deepCleanPkgName_tv);

        }
    }
}
