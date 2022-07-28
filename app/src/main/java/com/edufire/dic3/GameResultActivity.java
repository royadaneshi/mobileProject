package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.edufire.dic3.Models.User;

public class GameResultActivity extends AppCompatActivity {

    private static String username;
    private TextView time;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        final AppCompatButton startNewQuiz = findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswer = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswer = findViewById(R.id.incorrectAnswers);

        final int getCorrectAnswer = getIntent().getIntExtra("correct", 0);
        final int getIncorrectAnswer = getIntent().getIntExtra("incorrect", 0);
        username = getIntent().getStringExtra("userName");
        time = findViewById(R.id.time_result);

        User user = User.getAllUsers().get(username);
        if(3 * getCorrectAnswer - getIncorrectAnswer > 0)
            if (user != null) {
                user.increaseScore(3 * getCorrectAnswer - getIncorrectAnswer);
                MainActivity.db.updateUserScore(username, user.getScore(), user.isPremium());
            }

        String totalTimeInMin = getIntent().getStringExtra("totalTimeInMin");
        String seconds = getIntent().getStringExtra("seconds");

        if(totalTimeInMin != null && totalTimeInMin.length() == 1)
            totalTimeInMin = "0" + totalTimeInMin;
        if(seconds != null && seconds.length() == 1)
            seconds = "0" + seconds;

        time.setText("Time " + totalTimeInMin + ":" + seconds);

        correctAnswer.setText("Correct Answer : " + getCorrectAnswer);
        incorrectAnswer.setText("Wrong Answer : " + getIncorrectAnswer);

        startNewQuiz.setOnClickListener(view -> {
            Intent intent = new Intent(GameResultActivity.this, MainMenuActivity.class);
            intent.putExtra("userName", username);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameResultActivity.this, MainMenuActivity.class);
        intent.putExtra("userName",username);
        startActivity(intent);
        finish();
    }
}