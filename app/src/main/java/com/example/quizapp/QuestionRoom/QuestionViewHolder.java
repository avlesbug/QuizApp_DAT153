package com.example.quizapp.QuestionRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;

class QuestionViewHolder extends RecyclerView.ViewHolder {
    private final TextView questionItemView;

    private QuestionViewHolder(View itemView) {
        super(itemView);
        questionItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        questionItemView.setText(text);
    }

    static QuestionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new QuestionViewHolder(view);
    }
}
