package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.edufire.dic3.Models.User;
import com.edufire.dic3.Models.Word;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        String username = getIntent().getStringExtra("userName");
        String word = getIntent().getStringExtra("word");
        User user = User.getAllUsers().get(username);

        if(user != null) {
            ArrayList<String> words = new ArrayList<>();
            for (String str : MainActivity.db.getGroupCommon(username)) {
                for (Word word1 : MainActivity.db.getUserSearchWordFromDatabase(str)) {
                    if(word1.getWord().equals(word)){
                        Toast.makeText(this, word1.getWord(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    }
}