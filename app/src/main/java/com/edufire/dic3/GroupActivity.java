package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.edufire.dic3.Models.User;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
    }
    
    private void insertTeammateRequest(String sender_username, String receiver_username){
        if(User.getAllUsers().containsKey(receiver_username)){
            if(MainActivity.db.isTeammateRequestExistsInDatabase(sender_username, receiver_username)){
                Toast.makeText(this, "You have already submitted a request", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.db.insertTeammateRequest(sender_username, receiver_username);
            }
        } else {
            Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show();
        }
    }
}