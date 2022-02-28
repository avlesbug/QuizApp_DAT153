package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class SaveQuestionActivity extends AppCompatActivity {
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_question);
        question = new Question("something","Helge","Anders","Lars");

    }


    public void saveQuestion(Question question){
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(this.getApplicationContext());
        db.questionDao().insert(question);
        finish();
    }

    public void onClickSave(View view) {
        saveQuestion(question);
    }
}