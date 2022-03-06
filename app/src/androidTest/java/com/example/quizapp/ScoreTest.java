package com.example.quizapp;
import android.util.Log;


import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

//import com.example.quizapp.activity.QuizActivity;
import com.example.quizapp.QuizActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;


@RunWith(AndroidJUnit4.class)
public class ScoreTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);


    @Test
    public void TestCorrectScore(){
        onView(withId(R.id.submit_button))
                .perform(ViewActions.click());
        String name = QuizActivity.questions.get(0).getCorrectAnswer();
        QuizActivity.selectedOptionText = name;
        QuizActivity.selectedOption = R.id.twOption1;

        onView(withId(R.id.submit_button))
                    .perform(ViewActions.click());
        onView(withId(R.id.twScore)).check(matches(withSubstring("1")));
    }

    @Test
    public void TestWrongScore(){
        onView(withId(R.id.submit_button))
                .perform(ViewActions.click());
        String name = QuizActivity.questions.get(0).getCorrectAnswer();
        QuizActivity.selectedOptionText = name+"Wrong";
        QuizActivity.selectedOption = R.id.twOption1;

        onView(withId(R.id.submit_button))
                .perform(ViewActions.click());
        onView(withId(R.id.twScore)).check(matches(withSubstring("0")));
    }

}