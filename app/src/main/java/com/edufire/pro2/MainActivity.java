package com.edufire.pro2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.edufire.pro2.Models.APIResponse;
import com.edufire.pro2.Models.Definitions;
import com.edufire.pro2.Models.Meanings;
import com.edufire.pro2.Models.Phonetics;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestManager requestManager = new RequestManager(MainActivity.this);
        requestManager.getWordMeaning(listener, "hello");//put input word instead of 'hello'
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
            System.out.println("phonetics's text: " + phonetics.get(i).getText() );
            System.out.println("Audio link: " + phonetics.get(i).getAudio());


        }
        System.out.println("----------------------------------------------------------------------------------------------------\n");


    }
}