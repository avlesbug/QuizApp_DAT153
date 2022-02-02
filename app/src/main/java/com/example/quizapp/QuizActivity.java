package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    QuestionDB qDB = new QuestionDB();
    private int selectedOption;
    private Question activeQuestion;
    private int currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setQuestions();
        selectedOption = 0;
        currentScore = 0;
    }

    private void nextQuestion() {
        questions.remove(activeQuestion);
        if(!questions.isEmpty()) {
            activeQuestion = questions.get(0);
            updateOptions();
            updateImage();
            setDefaultBorder();
        }
        finishQuiz();
        setDefaultBorder();
    }

    private void setDefaultBorder() {
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    private void finishQuiz() {
    }

    private void setQuestions() {
        questions = qDB.createQuestions();
        activeQuestion = questions.get(0);
        updateOptions();
        updateImage();
    }

    private void updateImage() {

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(activeQuestion.getImageId());

    }

    private void updateOptions() {
        TextView option1 = findViewById(R.id.twOption1);
        TextView option2 = findViewById(R.id.twOption2);
        TextView option3 = findViewById(R.id.twOption3);

        option1.setText(activeQuestion.getOption1());
        option2.setText(activeQuestion.getOption2());
        option3.setText(activeQuestion.getOption3());

    }

    private boolean checkCorrect(){
        if(selectedOption == activeQuestion.getCorrectAnswer()){
            drawCoorectOpt(selectedOption);
            return true;
        }
        else{
            drawCoorectOpt(activeQuestion.getCorrectAnswer());
            drawWrong(selectedOption);
            return false;
        }
    }

    public void onClickOpt1(View view) {
        selectedOption = 1;
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    public void onCLickOpt2(View view) {
        selectedOption = 2;
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    public void onClickOpt3(View view) {
        selectedOption = 3;
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    public void onClickSub(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if(buttonText.equals("Submit")) {
            if (checkCorrect()) {
                currentScore++;
                TextView score = findViewById(R.id.twScore);
                score.setText(String.valueOf(currentScore));
            }
            button.setText("Next");
        }else if(buttonText.equals("Next")){
            nextQuestion();
            button.setText("Submit");
        }
    }

    public void drawCoorectOpt(Integer selected){
        switch(selected) {
            case 1:
                TextView tw = findViewById(R.id.twOption1);
                tw.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_bg));
                break;
            case 2:
                TextView tw2 = findViewById(R.id.twOption2);
                tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_bg));
                break;
            case 3:
                TextView tw3 = findViewById(R.id.twOption3);
                tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_bg));
                break;
        }
    }

    public void drawWrong(Integer selected){
        switch(selected){
            case 1:
                TextView tw = findViewById(R.id.twOption1);
                tw.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_option_bg));
                break;
            case 2:
                TextView tw2 = findViewById(R.id.twOption2);
                tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_option_bg));
                break;
            case 3:
                TextView tw3 = findViewById(R.id.twOption3);
                tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_option_bg));
                break;
        }
    }
}