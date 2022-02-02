package com.example.quizapp;

public class Question {

    private int imageId;
    private String option1;
    private String option2;
    private String option3;
    private int correctAnswer;

    public Question(int imageId, String option1, String option2, String option3, int correctAnswer){
        this.imageId = imageId;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.correctAnswer=correctAnswer;

    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getImageId() {
        return imageId;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String toString(){
        return (option1 + ", " + option2 + ", " + option3 + ", correct: " + correctAnswer);
    }

}
