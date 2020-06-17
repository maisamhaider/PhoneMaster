package com.example.phonemaster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanImagesModel;
import com.example.phonemaster.models.DeepCleanVideosModel;

import java.util.ArrayList;
import java.util.List;

public class DeepCleanVideosAdapter extends RecyclerView.Adapter<DeepCleanVideosAdapter.WhatsAppStatusHolder> {
    Context context;
    List<DeepCleanVideosModel> fileList;
    List<String> list;

    public DeepCleanVideosAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setFileList(List<DeepCleanVideosModel> fileList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deep_clean_videos_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String videoString = fileList.get(position).getVideoPath();


        Glide.with(context)
                .load(videoString)
                .into(holder.deepCleanVideosRv_iv);

        holder.selectVideo_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    list.add(videoString);
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

        ImageView deepCleanVideosRv_iv;
        CheckBox selectVideo_cb;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanVideosRv_iv = itemView.findViewById(R.id.deepCleanVideosRv_iv);
            selectVideo_cb = itemView.findViewById(R.id.selectVideo_cb);
        }
    }
}
