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

        if (list.contains(audioString)) {
            holder.selection_iv.setImageResource(R.drawable.ic_select);
        } else {
            holder.selection_iv.setImageResource(R.drawable.ic_deselect);
        }
        holder.commonFileName_tv.setText(audioName);


        holder.selection_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.contains(audioString)) {
                    list.add(audioString);
                    holder.selection_iv.setImageResource(R.drawable.ic_select);
                } else {
                    list.remove(position);
                    holder.selection_iv.setImageResource(R.drawable.ic_deselect);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    static class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView commonFileRv_iv, selection_iv;
        TextView commonFileName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            commonFileRv_iv = itemView.findViewById(R.id.commonFileRv_iv);
            commonFileName_tv = itemView.findViewById(R.id.commonFileName_tv);
            selection_iv = itemView.findViewById(R.id.selection_iv);

        }
    }
}
