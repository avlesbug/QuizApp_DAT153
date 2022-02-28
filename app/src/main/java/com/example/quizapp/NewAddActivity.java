package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewAddActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.questionlistsql.REPLY";

    private EditText mEditQuestionView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);
        mEditQuestionView = findViewById(R.id.edit_question);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditQuestionView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String question = mEditQuestionView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, question);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}