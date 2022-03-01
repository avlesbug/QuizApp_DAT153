package com.example.quizapp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class UploadListAdapter extends ListAdapter<Upload, UploadViewHolder> {

    public UploadListAdapter(@NonNull DiffUtil.ItemCallback<Upload> diffCallback) {
        super(diffCallback);
    }

    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UploadViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(UploadViewHolder holder, int position) {
        Upload current = getItem(position);
        holder.bind(current.getName());
    }

    static class UploadDiff extends DiffUtil.ItemCallback<Upload> {

        @Override
        public boolean areItemsTheSame(@NonNull Upload oldItem, @NonNull Upload newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Upload oldItem, @NonNull Upload newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}