package com.example.quizapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UploadRepository {
    private UploadDao mUploadDao;
    private LiveData<List<Upload>> mAllUploads;

    UploadRepository(Application application) {
        UploadRoomDatabase db = UploadRoomDatabase.getDatabase(application);
        mUploadDao = db.UploadDao();
        mAllUploads = mUploadDao.getAlphabetizedUploads();
    }

    LiveData<List<Upload>> getAllUploads(){
        return mAllUploads;
    }

    void insert (Upload upload){
        UploadRoomDatabase.databaseWriteExecutor.execute(() -> {
            mUploadDao.insert(upload);
        });
    }
}
