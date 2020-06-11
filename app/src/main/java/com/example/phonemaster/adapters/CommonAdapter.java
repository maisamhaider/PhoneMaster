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
import com.example.phonemaster.models.DeepCleanAudioModel;
import com.example.phonemaster.models.CommonModel;
import com.example.phonemaster.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.WhatsAppStatusHolder> {
    Context context;
    List<CommonModel> fileList;
    List<String> list;
    Utils utils;

    public CommonAdapter(Context context) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String pathString = fileList.get(position).getPath();
        final String name = fileList.get(position).getName();
        utils = new Utils(context);

        holder.commonFileName_tv.setText(name);
        if (pathString.endsWith("mp4"))
        {
            holder.commonIsVideo_iv.setVisibility(View.VISIBLE);
            Glide.with(context).load(pathString).into(holder.commonFileRv_iv);

        }
        else
            {
                holder.commonIsVideo_iv.setVisibility(View.INVISIBLE);

            }
     if (pathString.endsWith(String.valueOf(new Utils.AllDoFilter())))
        {
             Glide.with(context).load(R.drawable.ic_launcher_foreground).into(holder.commonFileRv_iv);
        }
        else if (pathString.endsWith(String.valueOf(new Utils.AllImgFilter())))
        {
             Glide.with(context).load(R.drawable.ic_launcher_foreground).into(holder.commonFileRv_iv);

        }
        else if (pathString.endsWith(String.valueOf(new Utils.AllAudioFilter())))
        {
             Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.commonFileRv_iv);

        }
        else if (pathString.endsWith(String.valueOf(new Utils.AllPackagesFilter())))
        {
             Glide.with(context).load(R.drawable.ic_launcher_foreground).into(holder.commonFileRv_iv);

        }


        holder.selectAudio_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    list.add(pathString);
                }
                else
                {
                    list.remove(pathString);
                }
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView commonFileRv_iv,commonIsVideo_iv;
        CheckBox selectAudio_cb;
        TextView commonFileName_tv,commonFileSize_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            commonIsVideo_iv = itemView.findViewById(R.id.commonIsVideo_iv);
            commonFileRv_iv = itemView.findViewById(R.id.commonFileRv_iv);
            selectAudio_cb = itemView.findViewById(R.id.selectCommon_cb);
            commonFileName_tv = itemView.findViewById(R.id.commonFileName_tv);
            commonFileSize_tv = itemView.findViewById(R.id.commonFileSize_tv);

        }
    }
}
