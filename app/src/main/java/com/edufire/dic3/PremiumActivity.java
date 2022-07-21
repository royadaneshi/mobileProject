package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edufire.dic3.Models.User;

public class PremiumActivity extends AppCompatActivity {

    EditText premiumCode;
    Button submit;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);
        premiumCode = findViewById(R.id.txt_premium);
        submit = findViewById(R.id.btn_submit_premium_code);
        user = User.getAllUsers().get(getIntent().getStringExtra("username"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = premiumCode.getText().toString();
                if (code.equals("")){
                    Toast.makeText(PremiumActivity.this,"please enter code",Toast.LENGTH_SHORT).show();
                }else{
                    submitPremiumCode(code);
                }
            }
        });
    }

    private void submitPremiumCode(String code){
        new PremiumRequest(user,code);
        Toast.makeText(this,"Code sent!",Toast.LENGTH_SHORT).show();
    }
}