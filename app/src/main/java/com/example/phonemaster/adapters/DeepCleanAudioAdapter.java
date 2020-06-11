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
import com.example.phonemaster.models.DeepCleanVideosModel;

import java.util.ArrayList;
import java.util.List;

public class DeepCleanAudioAdapter extends RecyclerView.Adapter<DeepCleanAudioAdapter.WhatsAppStatusHolder> {
    Context context;
    List<DeepCleanAudioModel> fileList;
    List<String> list;

    public DeepCleanAudioAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setFileList(List<DeepCleanAudioModel> fileList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deep_clean_audio_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String audioString = fileList.get(position).getAudioPath();
        final String audioName = fileList.get(position).getAudioName();

        holder.deepCleanAudioName_tv.setText(audioName);

        holder.selectAudio_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    list.add(audioString);
                }
                else
                {
                    list.remove(audioString);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView deepCleanAudioRv_iv;
        CheckBox selectAudio_cb;
        TextView deepCleanAudioName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanAudioRv_iv = itemView.findViewById(R.id.deepCleanAudioRv_iv);
            selectAudio_cb = itemView.findViewById(R.id.selectAudio_cb);
            deepCleanAudioName_tv = itemView.findViewById(R.id.deepCleanAudioName_tv);

        }
    }
}
