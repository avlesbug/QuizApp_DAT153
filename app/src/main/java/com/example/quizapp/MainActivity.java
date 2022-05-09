package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Quiz button */
    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivity(intent);
        // Do something in response to button
    }

    /** Called when the user taps the Add button */
    public void startAdd(View view) {
        Intent intent = new Intent(this, NewAddActivity.class);
        startActivity(intent);
        // Do something in response to button
    }

    /** Called when the user taps the Database button */
    public void startDatabase(View view) {
        Intent intent = new Intent(this, NewDBActivity.class);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivity(intent);
        // Do something in response to button
    }
}