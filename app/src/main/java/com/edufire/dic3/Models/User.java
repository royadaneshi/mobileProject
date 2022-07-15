package com.edufire.dic3.Models;


import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String username;//unique for each user
    String password;
    int limitRequestCounter;
    ArrayList<Word> searchWord = new ArrayList<>();
    static HashMap<String, User> allUsers = new HashMap<>();


    public User(String username, String password, ArrayList<Word> searchWord) {
        this.username = username;
        this.password = password;
        this.searchWord = searchWord;
    }

    public void setLimitRequestCounterIncrease() {
        limitRequestCounter++;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Word> getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(ArrayList<Word> searchWord) {
        this.searchWord = searchWord;
    }


}
