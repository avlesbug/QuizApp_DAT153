package com.example.quizapp.QuestionRoom;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizapp.Question;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository mRepository;

    private final LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel (Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mAllQuestions = mRepository.getAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions() { return mAllQuestions; }

    public void insert(Question question) { mRepository.insert(question); }
}

