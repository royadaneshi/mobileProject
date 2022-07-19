package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    TextView txtSearch,txtGroups,txtPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        txtSearch = findViewById(R.id.txt_menu_search);
        txtGroups = findViewById(R.id.txt_menu_groups);
        txtPremium = findViewById(R.id.txt_menu_premium);

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}