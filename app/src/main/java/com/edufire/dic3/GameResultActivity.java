package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameResultActivity extends AppCompatActivity {

    private static String username;

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