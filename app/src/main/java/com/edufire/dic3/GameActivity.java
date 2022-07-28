package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private TextView question, questions;
    private AppCompatButton option1, option2, option3, option4, nextBtn;
    private Timer quizTimer;
    private int totalTimeInMin = 0;
    private int seconds = 0;
    private int currentQuestionPosition = 0;
    private List<QuestionsList> questionsLists;
    private String selectedOptionByUser = "";
    private static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        username = getIntent().getStringExtra("userName");

        final ImageView backBtn = findViewById(R.id.back_btn_game);
        final TextView timer = findViewById(R.id.timer);

        question = findViewById(R.id.question);
        questions = findViewById(R.id.questions);

        option1 = findViewById(R.id.option_1);
        option2 = findViewById(R.id.option_2);
        option3 = findViewById(R.id.option_3);
        option4 = findViewById(R.id.option_4);

        nextBtn = findViewById(R.id.nextBtn);

        questionsLists = QuestionsBank.getAllQuestions();
        startTimer(timer);

        questions.setText((currentQuestionPosition + 1) + "/" + questionsLists.size() );
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(GameActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                } else {
                    changeNextQuestion();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizTimer.purge();
                quizTimer.cancel();

                Intent intent = new Intent(GameActivity.this, MainMenuActivity.class);
                intent.putExtra("userName", username);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startTimer(TextView timeTextview){
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;

                if(seconds == 60){
                    totalTimeInMin++;
                    seconds = 0;
                }

                if(totalTimeInMin == 20){
                    quizTimer.purge();
                    quizTimer.cancel();

                    Intent intent = new Intent(GameActivity.this, GameResultActivity.class);
                    intent.putExtra("correct", getCorrectAnswers());
                    intent.putExtra("incorrect", getIncorrectAnswers());
                    intent.putExtra("userName", username);
                    intent.putExtra("totalTimeInMin", String.valueOf(totalTimeInMin));
                    intent.putExtra("seconds", String.valueOf(seconds));
                    startActivity(intent);
                    finish();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimeInMin);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length() == 1){
                            finalMinutes = "0" + finalMinutes;
                        }

                        if(finalSeconds.length() == 1){
                            finalSeconds = "0" + finalSeconds;
                        }

                        timeTextview.setText(finalMinutes + ":" + finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }

    private void changeNextQuestion(){
        currentQuestionPosition++;
        if(currentQuestionPosition == questionsLists.size()){
            nextBtn.setText("Submit");
        }
        if(currentQuestionPosition < questionsLists.size()){
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1F6BBB"));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1F6BBB"));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1F6BBB"));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1F6BBB"));

            questions.setText((currentQuestionPosition + 1) + "/" + questionsLists.size() );
            question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());
        } else{
            Intent intent = new Intent(GameActivity.this, GameResultActivity.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("incorrect", getIncorrectAnswers());
            intent.putExtra("userName", username);
            intent.putExtra("totalTimeInMin", String.valueOf(totalTimeInMin));
            intent.putExtra("seconds", String.valueOf(seconds));
            startActivity(intent);
            finish();
        }
    }

    private int getCorrectAnswers(){
        int correctAnswers = 0;
        for (int i = 0; i < questionsLists.size(); i++) {
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers += 1;
            }
        }
        return correctAnswers;
    }

    private int getIncorrectAnswers(){
        int correctAnswers = 0;
        for (int i = 0; i < questionsLists.size(); i++) {
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswers += 1;
            }
        }
        return correctAnswers;
    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        Intent intent = new Intent(GameActivity.this, MainMenuActivity.class);
        intent.putExtra("userName", username);
        startActivity(intent);
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionPosition).getAnswer();
        if(option1.getText().toString().equals(getAnswer)){
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        } else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        } else if(option3.getText().toString().equals(getAnswer)){
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        } else if(option4.getText().toString().equals(getAnswer)){
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }
}