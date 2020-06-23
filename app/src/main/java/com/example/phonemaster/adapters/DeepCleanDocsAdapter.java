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
import com.example.phonemaster.models.DeepCleanDocsModel;

import java.util.ArrayList;
import java.util.List;

public class DeepCleanDocsAdapter extends RecyclerView.Adapter<DeepCleanDocsAdapter.WhatsAppStatusHolder> {
    Context context;
    List<DeepCleanDocsModel> fileList;
    List<String> list;

    public DeepCleanDocsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }


    public void setFileList(List<DeepCleanDocsModel> fileList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deep_clean_docs_rv_layout, parent, false);

        return new WhatsAppStatusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppStatusHolder holder, int position) {

        final String docsString = fileList.get(position).getDocPath();
        final String docName = fileList.get(position).getDocName();

        if (list.contains(docsString)) {
            holder.selectDocs_iv.setImageResource(R.drawable.ic_select);
        } else {
            holder.selectDocs_iv.setImageResource(R.drawable.ic_deselect);
        }
        holder.deepCleanDocName_tv.setText(docName);

        holder.selectDocs_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.contains(docsString)) {
                    list.add(docsString);
                    holder.selectDocs_iv.setImageResource(R.drawable.ic_select);
                } else {
                    list.remove(position);
                    holder.selectDocs_iv.setImageResource(R.drawable.ic_deselect);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView deepCleanDocsRv_iv,selectDocs_iv;
         TextView deepCleanDocName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanDocsRv_iv = itemView.findViewById(R.id.deepCleanDocsRv_iv);
            selectDocs_iv = itemView.findViewById(R.id.selectDocs_iv);
            deepCleanDocName_tv = itemView.findViewById(R.id.deepCleanDocName_tv);

        }
    }
}
