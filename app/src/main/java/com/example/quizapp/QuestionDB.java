package com.example.quizapp;

import java.util.ArrayList;

public class QuestionDB {
    public ArrayList<Question> createQuestions(){

        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(R.drawable.erna_solberg,"Trine Skei Grande","Erna Solberg","Hillary Clinton",2));
        questions.add(new Question(R.drawable.trine_skei_grande,"Erna Solberg","Hillary Clinton","Trine Skei Grande",3));
        questions.add(new Question(R.drawable.hillary_clinton,"Hillary Clinton","Trine Skei Grande","Erna Solberg",1));
        questions.add(new Question(R.drawable.jens_stoltenberg,"Jens Stoltenberg","Erna Solberg","Jonas Gahr Støre",1));
        questions.add(new Question(R.drawable.jonas_gahr_store,"Jens Stoltenberg","Jonas Gahr Støre","Bill Clinton",2));

        return questions;
    }
}
