package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.User;

public class register extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;
    TextView haveAccount;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername = findViewById(R.id.txt_register);
        txtPassword = findViewById(R.id.txt_register_password);
        haveAccount = findViewById(R.id.txt_go_login);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(view -> {
            String username = txtUsername.getText().toString();
            String password = txtPassword.getText().toString();
            if(User.getAllUsers().containsKey(username)){
                Toast.makeText(register.this, "this username is already exists", Toast.LENGTH_SHORT).show();
            } else {
                MainActivity.db.insertUserInformation(username, password, 0, 0, false, "");
                new User(username, password);
                Toast.makeText(register.this, "register successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(register.this, MainMenuActivity.class);
                intent.putExtra("userName", username);
                startActivity(intent);
            }
        });

        haveAccount.setOnClickListener(view -> {
            Intent intent = new Intent(register.this, MainActivity.class);
            startActivity(intent);
        });

    }
}