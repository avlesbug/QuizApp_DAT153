package com.example.quizapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {
    private QuestionDao mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;

    QuestionRepository(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDao();
        mAllQuestions = mQuestionDao.getAlphabetizedQuestions();
    }

    LiveData<List<Question>> getAllQuestions(){
        return mAllQuestions;
    }

    void insert (Question question){
        QuestionRoomDatabase.databaseWriteExecutor.execute(() -> {
            mQuestionDao.insert(question);
        });
    }
}
