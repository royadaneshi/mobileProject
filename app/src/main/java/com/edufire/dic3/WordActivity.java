package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.User;
import com.edufire.dic3.Models.Word;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        String username = getIntent().getStringExtra("userName");
        String word = getIntent().getStringExtra("word");
        User user = User.getAllUsers().get(username);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        Word wordClicked = null;

        if(user != null) {
            ArrayList<String> words = new ArrayList<>();
            for(String str : MainActivity.db.getGroupCommon(username)){
                for(Word word1 : MainActivity.db.getUserSearchWordFromDatabase(str)){
                    if(word1.getWord().equalsIgnoreCase(word))
                        wordClicked = word1;
                }
            }
            for(Word word2 : MainActivity.db.getUserSearchWordFromDatabase(username)){
                if(word2.getWord().equalsIgnoreCase(word))
                    wordClicked = word2;
            }
            if(wordClicked != null){
                textView1.setText(wordClicked.getRoleOfWordInSentence());
                textView2.setText(wordClicked.getDescription());
                textView3.setText(wordClicked.getWord());
            }
        }

    }
}