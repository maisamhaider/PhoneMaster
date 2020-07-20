package com.cleaner.booster.phone.repairer.app.adapters;

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
import com.cleaner.booster.phone.repairer.app.R;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanImagesModel;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanVideosModel;

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

        if (list.contains(videoString))
        {
            holder.selectVideo_iv.setImageResource(R.drawable.ic_select);

        }else
        {
            holder.selectVideo_iv.setImageResource(R.drawable.ic_deselect);

        }
        Glide.with(context)
                .load(videoString)
                .into(holder.deepCleanVideosRv_iv);


        holder.selectVideo_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.contains(videoString))
                {
                    list.add(videoString);
                    holder.selectVideo_iv.setImageResource(R.drawable.ic_select);
                }
                else
                {
                    list.remove(videoString);
                    holder.selectVideo_iv.setImageResource(R.drawable.ic_deselect);

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView deepCleanVideosRv_iv,selectVideo_iv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanVideosRv_iv = itemView.findViewById(R.id.deepCleanVideosRv_iv);
            selectVideo_iv = itemView.findViewById(R.id.selectVideo_iv);
        }
    }
}
