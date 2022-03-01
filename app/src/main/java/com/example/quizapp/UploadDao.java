package com.example.quizapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UploadDao {

    @Query("SELECT * FROM upload_table")
    List<Upload> getAll();

    @Query("SELECT * FROM upload_table WHERE mName IN (:userIds)")
    List<Upload> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM upload_table WHERE mName LIKE :answer LIMIT 1")
    Upload findByCorrectOption(String answer);

    @Query("SELECT * FROM upload_table ORDER BY mName ASC")
    LiveData<List<Upload>> getAlphabetizedUploads();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Upload upload);

    @Update
    public void updateUsers(Upload... uploads);


    @Delete
    void delete(Upload upload);

    @Query("DELETE FROM upload_table")
    void deleteAll();
}
