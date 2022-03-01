package com.example.quizapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UploadViewModel extends AndroidViewModel {
    private UploadRepository mRepository;

    private final LiveData<List<Upload>> mAllUploads;

    public UploadViewModel(Application application) {
        super(application);
        mRepository = new UploadRepository(application);
        mAllUploads = mRepository.getAllUploads();
    }

    LiveData<List<Upload>> getAllUploads() { return mAllUploads; }

    public void insert(Upload upload) { mRepository.insert(upload); }
}

