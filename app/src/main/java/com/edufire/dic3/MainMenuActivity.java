package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.edufire.dic3.Models.User;

public class MainMenuActivity extends AppCompatActivity {

    TextView txtSearch,txtGroups,txtPremium, txtPlayGame;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        txtSearch = findViewById(R.id.txt_menu_search);
        txtGroups = findViewById(R.id.txt_menu_groups);
        txtPremium = findViewById(R.id.txt_menu_premium);
        txtPlayGame = findViewById(R.id.playGame);

        String username = getIntent().getStringExtra("userName");
        String password = getIntent().getStringExtra("password");

        user = User.getAllUsers().get(username);

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,SearchActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        txtPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,PremiumActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        txtPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,GameActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}