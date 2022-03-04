package com.example.quizapp;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Question {

    private String correctAnswer;

    private String image;
    private String option1;
    private String option2;

    public Question(String image, String option1, String option2, String correctAnswer){
        this.image = image;
        this.option1=option1;
        this.option2=option2;
        this.correctAnswer=correctAnswer;

    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getImage() {
        return image;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }


    public String toString(){
        return (option1 + ", " + option2 + ", correct: " + correctAnswer);
    }

}
