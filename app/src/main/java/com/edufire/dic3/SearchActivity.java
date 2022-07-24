package com.edufire.dic3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.APIResponse;
import com.edufire.dic3.Models.Definitions;
import com.edufire.dic3.Models.Meanings;
import com.edufire.dic3.Models.Phonetics;
import com.edufire.dic3.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    Spinner spinnerFrom,spinnerTarget;
    String baseLanguage,targetLanguage;
    EditText text;
    TextView translation;
    Button submit;
    User user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        user = User.getAllUsers().get(getIntent().getStringExtra("username"));

        spinnerFrom = findViewById(R.id.spinner_search_from);
        spinnerTarget = findViewById(R.id.spinner_search_target);
        text = findViewById(R.id.txt_search_text);
        submit = findViewById(R.id.btn_search_submit);
        translation = findViewById(R.id.txt_search_result);

        translation.setVisibility(View.INVISIBLE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTarget.setAdapter(adapter);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                baseLanguage = parent.getItemAtPosition(i).toString();
                Log.d(baseLanguage, "onItemSelected-base: ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("in", "in");
                targetLanguage = adapterView.getItemAtPosition(i).toString();
                Log.d(targetLanguage, "onItemSelected-target: ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (user.canUserRequest()){
                    if (text.getText().toString().equals("")){
                        Toast.makeText(getParent(),"please fill text field", Toast.LENGTH_SHORT).show();
                    }else {
                        if (baseLanguage.equals(targetLanguage)){
                            Intent intent = new Intent(SearchActivity.this,TranslationActivity.class);
                            intent.putExtra("text",text.getText().toString());
                            startActivity(intent);
                        }else{
                            translation.setVisibility(View.VISIBLE);
                            String response = translateText(getLanguageCode(baseLanguage.toString()),getLanguageCode(targetLanguage),text.getText().toString());
                            response.replace("\r","");
                            translation.setText(response);
                        }
                        user.increaseLimitRequestCounter();
                    }
                } else{
                    Toast.makeText(SearchActivity.this,"You have reached your limit please buy premium plan to continue",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SearchActivity.this,PremiumActivity.class);
                    intent.putExtra("username",user.getUsername());
                    startActivity(intent);
                }

            }
        });

    }

    public String translateText(int fromLanguageCode, int toLanguageCode, String source) {
        final String[] answer = {""};
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();
        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();
        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        System.out.println(s);
                        answer[0] = s;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failed to translate");
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Fail to download the model!++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        });

        return answer[0];
    }

    public int getLanguageCode(String language) {
        int languageCode;
        switch (language) {
            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;
            case "Afrikaans":
                languageCode = FirebaseTranslateLanguage.AF;
                break;
            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;
            case "Belarusian":
                languageCode = FirebaseTranslateLanguage.BE;
                break;
            case "Czech":
                languageCode = FirebaseTranslateLanguage.CS;
                break;
            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;
            default:
                languageCode = 0;
        }
        return languageCode;
    }


}