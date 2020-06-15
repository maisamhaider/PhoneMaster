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

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanAudioModel;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String audioString = fileList.get(position).getAudioPath();
        final String audioName = fileList.get(position).getAudioName();

        holder.commonFileName_tv.setText(audioName);

        holder.selectAudio_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    list.add(audioString);
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
