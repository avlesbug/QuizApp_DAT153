package com.example.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class UploadViewHolder extends RecyclerView.ViewHolder {
    private final TextView uploadItemView;

    private UploadViewHolder(View itemView) {
        super(itemView);
        uploadItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        uploadItemView.setText(text);
    }

    static UploadViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new UploadViewHolder(view);
    }
}
