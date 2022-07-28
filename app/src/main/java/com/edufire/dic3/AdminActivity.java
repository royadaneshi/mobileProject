package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView, wordSearchNumber;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView = findViewById(R.id.userRecyclerView);
        wordSearchNumber = findViewById(R.id.wordSearchNumber);
        int count = 0;

        UserAdapter userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<User> users = new ArrayList<>();

        for (Map.Entry<String, User> entry : User.getAllUsers().entrySet()) {
            users.add(entry.getValue());
            count += entry.getValue().getSearchWord().size();
        }
        textView = findViewById(R.id.userNumber);
        textView.setText("number of users : " + users.size());
        wordSearchNumber.setText("number of words " + count);
        userAdapter.setUsers(users);
    }

}