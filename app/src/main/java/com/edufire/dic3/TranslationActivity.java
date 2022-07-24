package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edufire.dic3.Models.APIResponse;
import com.edufire.dic3.Models.Definitions;
import com.edufire.dic3.Models.Meanings;
import com.edufire.dic3.Models.Phonetics;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TranslationActivity extends AppCompatActivity {

    String text;
    TextView word;
    List<Phonetics> phoneticsList;
    List<Meanings> meaningsList;
    RecyclerView phoneticsRecycleView;
    phoneticsHandler phoneticsAdapter;
    RecyclerView meaningsRecycleView;
    MeaningsAdapter meaningsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        text = getIntent().getExtras().getString("text");
        word = findViewById(R.id.txt_translation_word);
        phoneticsRecycleView = findViewById(R.id.rv_translation_phonetics);
        meaningsRecycleView = findViewById(R.id.rv_translation_meanings);
        RequestManager requestManager = new RequestManager(TranslationActivity.this);
        requestManager.getWordMeaning(listener, text);//put input word instead of 'hello'
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            if (apiResponse == null) {
                Toast.makeText(TranslationActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                return;
            }
            phoneticsList = apiResponse.getPhonetics();
            meaningsList = apiResponse.getMeanings();
            word.setText(apiResponse.getWord());
            phoneticsRecycleView.setLayoutManager(new LinearLayoutManager(TranslationActivity.this));
            phoneticsAdapter = new phoneticsHandler(TranslationActivity.this,phoneticsList);
            phoneticsRecycleView.setAdapter(phoneticsAdapter);
            meaningsRecycleView.setLayoutManager(new LinearLayoutManager(TranslationActivity.this));
            meaningsAdapter = new MeaningsAdapter(TranslationActivity.this,meaningsList);
            meaningsRecycleView.setAdapter(meaningsAdapter);
//            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(TranslationActivity.this, "Error!", Toast.LENGTH_SHORT).show();
        }
    };

    private static void showData(APIResponse apiResponse) {
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
            System.out.println("phonetics's text: " + phonetics.get(i).getText() + "Audio link: " + phonetics.get(i).getAudio());

        }
        System.out.println("----------------------------------------------------------------------------------------------------\n");

    }
}