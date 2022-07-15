package com.edufire.dic3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.edufire.dic3.Models.APIResponse;
import com.edufire.dic3.Models.Definitions;
import com.edufire.dic3.Models.Meanings;
import com.edufire.dic3.Models.Phonetics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public  static DBHelper searchWordsDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        UserDataBase userDataBase = new UserDataBase(MainActivity.this);
        searchWordsDAO = new DBHelper(MainActivity.this);
        searchWordsDAO.insertData("Yaghi", "1234", "Hello","", "","", "","", "","", "","", "", "");

        System.out.println(translateText(getLanguageCode("English"), getLanguageCode("Belarusian"), "dream"));


        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getWordMeaning(listener, "hello");//put input word instead of 'hello'
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


    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            if (apiResponse == null) {
                Toast.makeText(MainActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    };


    // we have definition , PartOfSpeech , example , synonyms ,antonyms ,phonetics's text and audio link from the api response
    private void showData(APIResponse apiResponse) {
        System.out.println("----------------------------------------------------------------------------------------------------");

        System.out.println(apiResponse.getWord());

        List<Meanings> meaningsList = apiResponse.getMeanings();

        for (int i = 0; i < meaningsList.size(); i++) {
            System.out.println("PartOfSpeech: " + meaningsList.get(i).getPartOfSpeech());
            List<Definitions> definitionsList = meaningsList.get(i).getDefinitions();
            for (int j = 0; j < definitionsList.size(); j++) {
                System.out.println("Definition: " + definitionsList.get(j).getDefinition());
                System.out.println("Example: " + definitionsList.get(j).getExample());
                List<String> synonyms = definitionsList.get(j).getSynonyms();
                List<String> antonyms = definitionsList.get(j).getAntonyms();
                for (int k = 0; k < synonyms.size(); k++) {
                    System.out.println("synonyms: " + synonyms.get(k));
                }
                for (int k = 0; k < antonyms.size(); k++) {
                    System.out.println("antonyms: " + antonyms.get(k));
                }
            }
        }

        List<Phonetics> phonetics = apiResponse.getPhonetics();
        for (int i = 0; i < phonetics.size(); i++) {
            System.out.println("phonetics's text: " + phonetics.get(i).getText());
            System.out.println("Audio link: " + phonetics.get(i).getAudio());


        }
        System.out.println("----------------------------------------------------------------------------------------------------\n");


    }

}