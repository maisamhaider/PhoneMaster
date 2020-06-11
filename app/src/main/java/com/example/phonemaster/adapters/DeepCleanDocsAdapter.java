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


        holder.deepCleanDocName_tv.setText(docName);

        holder.selectDocs_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(docsString);
                } else {
                    list.remove(docsString);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fileList.size();
    }

    class WhatsAppStatusHolder extends RecyclerView.ViewHolder {

        ImageView deepCleanDocsRv_iv;
        CheckBox selectDocs_cb;
        TextView deepCleanDocName_tv;

        public WhatsAppStatusHolder(@NonNull View itemView) {
            super(itemView);

            deepCleanDocsRv_iv = itemView.findViewById(R.id.deepCleanDocsRv_iv);
            selectDocs_cb = itemView.findViewById(R.id.selectDocs_cb);
            deepCleanDocName_tv = itemView.findViewById(R.id.deepCleanDocName_tv);

        }
    }
}
