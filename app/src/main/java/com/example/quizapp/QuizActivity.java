package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.QuestionRoom.QuestionDB;
import com.example.quizapp.QuestionRoom.QuestionRoomDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<Question> questions;
    QuestionDB qDB = new QuestionDB();
    private int selectedOption;
    private String selectedOptionText;
    private Question activeQuestion;
    private int currentScore;
    private DatabaseReference mDatabaseReferance;
    private ImageDB quizImageDB;
    private int correctAnswerId;
    private QuestionRoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizImageDB = new ImageDB();
        selectedOption = 0;
        selectedOptionText = "";
        currentScore = 0;
        db = Room.databaseBuilder(getApplicationContext(),
                QuestionRoomDatabase.class, "database-question").build();


        //path to databasefiles. Pictures are saved in "uploads" folder.
        mDatabaseReferance = FirebaseDatabase.getInstance().getReference("test");

        mDatabaseReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnap) {
                for (DataSnapshot postSnapshot : dataSnap.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    quizImageDB.addImage(upload);
                }
            }
            // if error happens display a message
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuizActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //setQuestions();
    }

    //loads new question
    private void nextQuestion() {
        questions.remove(activeQuestion);
        if(!questions.isEmpty()) {
            activeQuestion = questions.get(0);
            updateOptions();
            updateImage();
            setDefaultBorder();
        } else{
            setQuestions();
            setDefaultBorder();
        }
    }

    //set the border around the answer options
    private void setDefaultBorder() {
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }


    //sets the first question when the activity is lanched
    private void setQuestions() {
        questions = qDB.createQuestions(quizImageDB);
        activeQuestion = questions.get(0);
        updateOptions();
        updateImage();
        setDefaultBorder();
    }

    //updates the imageView
    private void updateImage() {

        ImageView image = findViewById(R.id.imageView);
        Picasso.with(this).load(activeQuestion.getImageUrl()).into(image);
    }

    //updates option buttons with new answer options
    private void updateOptions() {
        TextView option1 = findViewById(R.id.twOption1);
        TextView option2 = findViewById(R.id.twOption2);
        TextView option3 = findViewById(R.id.twOption3);

        List<String> options = new ArrayList<>();
        options.add(activeQuestion.getOption1());
        options.add(activeQuestion.getOption2());
        options.add(activeQuestion.getCorrectAnswer());

        Random rand = new Random();
        String randomOption1 = options.get(rand.nextInt(options.size()));
        options.remove(randomOption1);

        option1.setText(randomOption1);

        String randomOption2 = options.get(rand.nextInt(options.size()));
        options.remove(randomOption2);

        option2.setText(randomOption2);

        String lastOption = options.get(0);
        options.remove(lastOption);

        option3.setText(lastOption);

        if(randomOption1.equals(activeQuestion.getCorrectAnswer())){
            correctAnswerId = option1.getId();
        } else if(randomOption2.equals(activeQuestion.getCorrectAnswer())){
            correctAnswerId = option2.getId();
        }else if(lastOption.equals(activeQuestion.getCorrectAnswer())){
            correctAnswerId = option3.getId();
        }

    }

    //checks if the selected option is the correct answer
    private boolean checkCorrect(){
        if(selectedOptionText == activeQuestion.getCorrectAnswer()){
            drawCoorectOpt(selectedOption);
            return true;
        }
        else{
            System.out.println("Selected: " + selectedOptionText + " but correct option was: " + activeQuestion.getCorrectAnswer());
            drawCoorectOpt(correctAnswerId);
            drawWrong(selectedOption);
            return false;
        }
    }

    //adds a border around the clicked option to provide feedback for which one is currently selected
    public void onClickOpt1(View view) {
        selectedOption = view.getId();
        TextView button = findViewById(view.getId());
        selectedOptionText = button.getText().toString();

        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    public void onCLickOpt2(View view) {
        selectedOption = view.getId();
        TextView button = findViewById(view.getId());
        selectedOptionText = button.getText().toString();

        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    public void onClickOpt3(View view) {
        selectedOption = view.getId();
        TextView button = findViewById(view.getId());
        selectedOptionText = button.getText().toString();

        TextView tw3 = findViewById(R.id.twOption3);
        tw3.setBackground(ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg));
        TextView tw = findViewById(R.id.twOption1);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
        TextView tw2 = findViewById(R.id.twOption2);
        tw2.setBackground(ContextCompat.getDrawable(this, R.drawable.default_option_border_bg));
    }

    //defines what happens when the submit/next button is clicked
    public void onClickSub(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if(buttonText.equals("Start")) {
            setQuestions();
            button.setText("Submit");

            //if the button text is "Submit" the answer gets checked, the score gets updated and the text of the button is changed to "Next"
        }else if(buttonText.equals("Submit")) {
            if (checkCorrect()) {
                currentScore++;
                TextView score = findViewById(R.id.twScore);
                score.setText(String.valueOf(currentScore));
            }
            button.setText("Next");
            //if the button text is "Next" it updates the question and set the text to "Submit2"
        }else if(buttonText.equals("Next")){
            nextQuestion();
            button.setText("Submit");
        }
    }

    //changes the background collor of the correct answer to green
    public void drawCoorectOpt(Integer twId){
        TextView tw = findViewById(twId);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_option_bg));
    }

    //if a wrong option is selected the background color gets changed to red
    public void drawWrong(Integer twId){
        TextView tw = findViewById(twId);
        tw.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_option_bg));
    }

    public void onClickSave(View view) {
        saveQuestion();
    }
    public void saveQuestion(){
        db.questionDao().insert(activeQuestion);
        finish();
    }
}