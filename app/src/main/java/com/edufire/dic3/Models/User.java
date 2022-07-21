package com.edufire.dic3.Models;


import java.util.HashMap;

public class User {
    String username;//unique for each user
    String password;
    int limitRequestCounter;
    boolean isPremium;
    String premiumCode;
    int requestCounter;
    static HashMap<String, User> allUsers = new HashMap<>();


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        limitRequestCounter = 0;
        requestCounter = 0;
        isPremium = false;
        premiumCode="";
        allUsers.put(username, this);
    }


    public void makeUserPremium(String premiumCode) {
        isPremium = true;
        this.premiumCode =premiumCode;
    }

    public boolean canUserRequest() {
        return isPremium || (!isPremium && requestCounter <= 10);
    }

    public int getRequestCounter() {
        return requestCounter;
    }

    public void addOneRequestForUser() {
        requestCounter++;
    }

    public void setUserPremium() {
        isPremium = true;
    }

    public boolean isPremium() {
        return isPremium;
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

}
