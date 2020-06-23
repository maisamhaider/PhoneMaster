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

import java.util.ArrayList;
import java.util.List;

public class DeepCleanImagesAdapter extends RecyclerView.Adapter<DeepCleanImagesAdapter.WhatsAppStatusHolder> {
    Context context;
    List<DeepCleanImagesModel> fileList;
    List<String> list;

    public DeepCleanImagesAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setFileList(List<DeepCleanImagesModel> fileList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deep_clean_images_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String imageString = fileList.get(position).getImagePath();

        if (list.contains(imageString))
        {
            holder.selectImage_iv.setImageResource(R.drawable.ic_select);
        }
        else
        {
         holder.selectImage_iv.setImageResource(R.drawable.ic_deselect);
        }


        Glide.with(context)
                .load(imageString)
                .into(holder.deepCleanRv_iv);

        holder.selectImage_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (list.contains(imageString))
               {
                   list.remove(imageString);
                   holder.selectImage_iv.setImageResource(R.drawable.ic_deselect);
               }
               else
               {
                   list.add(imageString);
                   holder.selectImage_iv.setImageResource(R.drawable.ic_select);
               }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {
        ImageView deepCleanRv_iv,selectImage_iv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanRv_iv = itemView.findViewById(R.id.deepCleanRv_iv);
            selectImage_iv = itemView.findViewById(R.id.selectImage_iv);
         }
    }
}
