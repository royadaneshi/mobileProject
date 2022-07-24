package com.edufire.dic3.Models;


import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String username;//unique for each user
    String password;
    int limitRequestCounter;
    boolean isPremium;
    String premiumCode;
    ArrayList<Word> searchWord = new ArrayList<Word>();

    static HashMap<String, User> allUsers = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        limitRequestCounter = 0;
        isPremium = false;
        premiumCode = "";
        allUsers.put(username, this);
    }

    public void makeUserPremium(String premiumCode) {
        isPremium = true;
        this.premiumCode = premiumCode;
    }

    public boolean canUserRequest() {
        return isPremium || (!isPremium && limitRequestCounter <= 10);
    }

    public int getLimitRequestCounter() {
        return limitRequestCounter;
    }

    public void setUserPremium(boolean premium) {
        isPremium = premium;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setLimitRequestCounter(int limitRequestCounter) {
        this.limitRequestCounter = limitRequestCounter;
    }

    public void increaseLimitRequestCounter() {
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
