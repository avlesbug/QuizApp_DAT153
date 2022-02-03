package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDB {
    public ArrayList<Question> createQuestions(ImageDB imageDB){

        ArrayList<Question> questions = new ArrayList<>();

        System.out.println("Size:" + imageDB.getUploads().size());

        for(Upload u : imageDB.getUploads()){
            List<String> allOptions = imageDB.getAllNames();
            Random rand = new Random();
            String randomOption1 = allOptions.get(rand.nextInt(allOptions.size()));
            String randomOption2 = allOptions.get(rand.nextInt(allOptions.size()));
            String correctOption = u.getName();
            Question question = new Question(u.getImageUrl(),randomOption1,randomOption2,correctOption);
            questions.add(question);
        }

        return questions;
    }
}
