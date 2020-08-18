package com.cleaner.booster.phone.repairer.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.models.CommonModel;

import java.util.ArrayList;
import java.util.List;

public class DeepCleanAudioAdapter extends RecyclerView.Adapter<DeepCleanAudioAdapter.WhatsAppStatusHolder> {
    Context context;
    List<CommonModel> fileList;
    List<String> list;

    public DeepCleanAudioAdapter(Context context) {
        this.context = context;
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

        final String audioString = fileList.get(position).getPath();
        final String audioName = fileList.get(position).getPath();

        if (list.contains(audioString)) {
            holder.selection_iv.setImageResource(R.drawable.ic_select);
        } else {
            holder.selection_iv.setImageResource(R.drawable.ic_deselect);
        }

        holder.commonFileName_tv.setText(audioName);
        Glide.with(context).load(R.drawable.ic_audio).into(holder.commonFileRv_iv);

        holder.selection_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String audioString = fileList.get(position).getPath();
                if (list.contains(audioString))
                {
                    list.remove(audioString);
                    holder.selection_iv.setImageResource(R.drawable.ic_deselect);
                }
                else
                {
                    list.add(audioString);
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
