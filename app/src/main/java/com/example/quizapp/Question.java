package com.example.quizapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    private String imageUrl;
    private String option1;
    private String option2;
    private String correctAnswer;

    public Question(String imageUrl, String option1, String option2, String correctAnswer){
        this.imageUrl = imageUrl;
        this.option1=option1;
        this.option2=option2;
        this.correctAnswer=correctAnswer;

    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public int getUid() {
        return uid;
    }


    public String toString(){
        return (option1 + ", " + option2 + ", correct: " + correctAnswer);
    }

}
