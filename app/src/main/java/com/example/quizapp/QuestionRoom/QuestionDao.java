package com.example.quizapp.QuestionRoom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quizapp.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM question_table")
    List<Question> getAll();

    @Query("SELECT * FROM question_table WHERE uid IN (:userIds)")
    List<Question> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM question_table WHERE correctanswer LIKE :answer LIMIT 1")
    Question findByCorrectOption(String answer);

    @Query("SELECT * FROM question_table ORDER BY correctAnswer ASC")
    LiveData<List<Question>> getAlphabetizedQuestions();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Question question);

    @Update
    public void updateUsers(Question... questions);


    @Delete
    void delete(Question question);

    @Query("DELETE FROM question_table")
    void deleteAll();
}
