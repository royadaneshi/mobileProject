package com.edufire.dic3.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Word {
    String word;
    ArrayList<String> meaning;
    String audioLink;
    String description;
    ArrayList<String> examples;
    String roleOfWordInSentence;
    ArrayList<String> synonyms;
    static HashMap<String, Word> allWords = new HashMap<>();

    public Word(String word, ArrayList<String> meaning, String audioLink, String description, ArrayList<String> examples, String roleOfWordInSentence, ArrayList<String> synonyms) {
        this.word = word;
        this.meaning = meaning;
        this.audioLink = audioLink;
        this.description = description;
        this.examples = examples;
        this.roleOfWordInSentence = roleOfWordInSentence;
        this.synonyms = synonyms;
        allWords.put(word, this);
    }

    public void setMeaning(ArrayList<String> meaning) {
        this.meaning = meaning;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExamples(ArrayList<String> examples) {
        this.examples = examples;
    }

    public void setRoleOfWordInSentence(String roleOfWordInSentence) {
        this.roleOfWordInSentence = roleOfWordInSentence;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public static Word getWordByWordName(String wordName) {
        return allWords.get(wordName);
    }

    public ArrayList<String> getMeaning() {
        return meaning;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getExamples() {
        return examples;
    }

    public String getRoleOfWordInSentence() {
        return roleOfWordInSentence;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public static HashMap<String, Word> getAllWords() {
        return allWords;
    }
}
