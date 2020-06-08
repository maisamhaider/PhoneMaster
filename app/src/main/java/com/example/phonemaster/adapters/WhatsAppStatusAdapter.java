package com.example.phonemaster.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phonemaster.R;
import com.example.phonemaster.activities.OnImageVideoAct;

import java.io.File;
import java.util.List;

public class WhatsAppStatusAdapter extends RecyclerView.Adapter<WhatsAppStatusAdapter.WhatsAppStatusHolder> {
    Context context;
    List<File> fileList;

    public WhatsAppStatusAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;

    }

    @NonNull
    @Override
    public WhatsAppStatusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.whats_app_saver_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String imageVideoString = fileList.get(position).getAbsolutePath();
        if (imageVideoString.endsWith("mp4"))
        {
            holder.isVideo_iv.setVisibility(View.VISIBLE);
        }
        else
            {
                holder.isVideo_iv.setVisibility(View.GONE);
            }

        Glide.with(context)
                .load(imageVideoString)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OnImageVideoAct.class);
                intent.putExtra("imageOrVideoPath",imageVideoString);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView imageView,isVideo_iv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.whatsAppRv_iv);
            isVideo_iv = itemView.findViewById(R.id.isVideo_iv);
        }
    }
}
