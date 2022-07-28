package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.User;


import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static DBHelper db;
    EditText txtUsername, txtPassword;
    TextView noAccount;
    Button btnLogin;
    HashMap<String, String> adminUsers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(MainActivity.this);
        db.getAllUserFromDataBase();

        txtUsername = findViewById(R.id.inputUsername);
        txtPassword = findViewById(R.id.inputPassword);
        noAccount = findViewById(R.id.registerNow);
        btnLogin = findViewById(R.id.btn_login);


        adminUsers.put("roya", "roya123");
        adminUsers.put("amir", "amir123");
        adminUsers.put("ali", "ali123");

        btnLogin.setOnClickListener(view -> {
            String username = txtUsername.getText().toString();
            String password = txtPassword.getText().toString();

            if (adminUsers.containsKey(username)) {
                if (Objects.equals(adminUsers.get(username), password)) {
                    Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                User user = User.getAllUsers().get(username);
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "You don't have an account", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.getPassword().equals(password)) {
                        Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                        intent.putExtra("userName", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        noAccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, register.class);
            startActivity(intent);
        });


//        System.out.println(translateText(getLanguageCode("English"), getLanguageCode("Belarusian"), "dream"));
//
//
//        RequestManager requestManager = new RequestManager(MainActivity.this);
//        requestManager.getWordMeaning(listener, "hello");//put input word instead of 'hello'
    }
//
//    private void loadData() {
//        HashMap<String, User> allUser = db.getAllUserFromDataBase();
//        if (allUser != null)
//            User.setAllUsers(allUser);
//    }


//    public String translateText(int fromLanguageCode, int toLanguageCode, String source) {
//        final String[] answer = {""};
//        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
//                .setSourceLanguage(fromLanguageCode)
//                .setTargetLanguage(toLanguageCode)
//                .build();
//        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
//        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();
//        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
//                    @Override
//                    public void onSuccess(String s) {
//                        System.out.println(s);
//                        answer[0] = s;
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println("Failed to translate");
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                System.out.println("Fail to download the model!++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//            }
//        });
//        return answer[0];
//    }

//    public int getLanguageCode(String language) {
//        int languageCode;
//        switch (language) {
//            case "English":
//                languageCode = FirebaseTranslateLanguage.EN;
//                break;
//            case "Afrikaans":
//                languageCode = FirebaseTranslateLanguage.AF;
//                break;
//            case "Arabic":
//                languageCode = FirebaseTranslateLanguage.AR;
//                break;
//            case "Belarusian":
//                languageCode = FirebaseTranslateLanguage.BE;
//                break;
//            case "Czech":
//                languageCode = FirebaseTranslateLanguage.CS;
//                break;
//            case "Hindi":
//                languageCode = FirebaseTranslateLanguage.HI;
//                break;
//            default:
//                languageCode = 0;
//        }
//        return languageCode;
//    }


//    private final OnFetchDataListener listener = new OnFetchDataListener() {
//        @Override
//        public void onFetchData(APIResponse apiResponse, String message) {
//            if (apiResponse == null) {
//                Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            showData(apiResponse);
//        }
//
//        @Override
//        public void onError(String message) {
//            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//        }
//    };


    // we have definition , PartOfSpeech , example , synonyms ,antonyms ,phonetics's text and audio link from the api response
//    private void showData(APIResponse apiResponse) {
//        System.out.println("----------------------------------------------------------------------------------------------------");
//
//        System.out.println(apiResponse.getWord());
//
//        List<Meanings> meaningsList = apiResponse.getMeanings();
//
//        for (int i = 0; i < meaningsList.size(); i++) {
//            System.out.println("PartOfSpeech: " + meaningsList.get(i).getPartOfSpeech());
//            List<Definitions> definitionsList = meaningsList.get(i).getDefinitions();
//            for (int j = 0; j < definitionsList.size(); j++) {
//                System.out.println("Definition: " + definitionsList.get(j).getDefinition());
//                System.out.println("Example: " + definitionsList.get(j).getExample());
//                List<String> synonyms = definitionsList.get(j).getSynonyms();
//                List<String> antonyms = definitionsList.get(j).getAntonyms();
//                for (int k = 0; k < synonyms.size(); k++) {
//                    System.out.println("synonyms: " + synonyms.get(k));
//                }
//                for (int k = 0; k < antonyms.size(); k++) {
//                    System.out.println("antonyms: " + antonyms.get(k));
//                }
//            }
//        }
//
//        List<Phonetics> phonetics = apiResponse.getPhonetics();
//        for (int i = 0; i < phonetics.size(); i++) {
//            System.out.println("phonetics's text: " + phonetics.get(i).getText());
//            System.out.println("Audio link: " + phonetics.get(i).getAudio());
//
//
//        }
//        System.out.println("----------------------------------------------------------------------------------------------------\n");
//
//
//    }

}