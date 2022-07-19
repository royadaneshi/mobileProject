package com.edufire.dic3;

import androidx.appcompat.app.AppCompatActivity;

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

public class TranslationActivity extends AppCompatActivity {

    String text;
    TextView word;
    List<Phonetics> phoneticsList;
    List<Meanings> meaningsList;
    MediaPlayer mediaPlayer;
    ImageView playUS,playUK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        text = getIntent().getExtras().getString("text");
        playUS = findViewById(R.id.audio_player_us);
        playUK = findViewById(R.id.audio_player_uk);
        word = findViewById(R.id.txt_translation_word);
        RequestManager requestManager = new RequestManager(TranslationActivity.this);
        requestManager.getWordMeaning(listener, text);//put input word instead of 'hello'
        
        playUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio("us");
            }
        });

        playUK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio("uk");
            }
        });
    }

    private void playAudio(String pronounce) {

        ArrayList<String> audios = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);

        for (Phonetics phonetic : phoneticsList){
            audios.add(phonetic.getAudio());
        }

        if (pronounce.equals("uk")){
            try {
                mediaPlayer.setDataSource(audios.get(1));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (pronounce.equals("us")){
            try {
                mediaPlayer.setDataSource(audios.get(0));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();
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
            System.out.println("phonetics's text: " + phonetics.get(i).getText());
            System.out.println("Audio link: " + phonetics.get(i).getAudio());


        }
        System.out.println("----------------------------------------------------------------------------------------------------\n");

    }
}