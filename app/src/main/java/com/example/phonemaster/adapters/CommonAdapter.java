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
import com.example.phonemaster.models.CommonModel;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.WhatsAppStatusHolder> {
    Context context;
    List<CommonModel> fileList;
    List<String> list;
    Utils utils;
    private int fileType=0;
    public static final int AUDIO=1;
    public static final int VIDEO=2;
    public static final int IMAGE=3;
    public static final int DOCUMENT=4;
    public static final int BACKUP=5;

    public CommonAdapter(Context context,int fileType) {
        this.context = context;
        this.fileType = fileType;
        list = new ArrayList<>();

    }


    public void setFileList(List<CommonModel> fileList) {
        this.fileList = fileList;
        for (CommonModel commonModel:fileList){
            list.add(commonModel.getPath());
        }
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String pathString = fileList.get(position).getPath();
        final String name = fileList.get(position).getName();
        utils = new Utils(context);

        if (list.contains(pathString)) {
            holder.selection_iv.setImageResource(R.drawable.ic_select);
        } else {
            holder.selection_iv.setImageResource(R.drawable.ic_deselect);
        }
        holder.commonFileName_tv.setText(name);

        if (fileType == VIDEO || fileType == IMAGE)
        {
            Glide.with(context).load(pathString).into(holder.commonFileRv_iv);
        } else if (fileType==AUDIO) {
            holder.commonFileRv_iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_audio));
        } else if (fileType==DOCUMENT) {
            holder.commonFileRv_iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_received_file));
        } else if (fileType==BACKUP) {
            holder.commonFileRv_iv.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_backup_conversation));
        }

        holder.selection_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pathString = fileList.get(position).getPath();
                if (list.contains(pathString))
                {
                    list.remove(pathString);
                    holder.selection_iv.setImageResource(R.drawable.ic_deselect);
                }
                else
                {
                    list.add(pathString);
                    holder.selection_iv.setImageResource(R.drawable.ic_select);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView commonFileRv_iv,selection_iv;
        TextView commonFileName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            commonFileRv_iv = itemView.findViewById(R.id.commonFileRv_iv);
            selection_iv = itemView.findViewById(R.id.selection_iv);
            commonFileName_tv = itemView.findViewById(R.id.commonFileName_tv);

        }
    }
}
