package com.edufire.dic3.Models;


import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String username;//unique for each user
    String password;
    int limitRequestCounter;
    ArrayList<Word> searchWord = new ArrayList<Word>();

    public int getLimitRequestCounter() {
        return limitRequestCounter;
    }

    static HashMap<String, User> allUsers = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        limitRequestCounter = 0;
        allUsers.put(username, this);
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

    public static void setAllUsers(HashMap<String, User> allUsers) {
        User.allUsers = allUsers;
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
