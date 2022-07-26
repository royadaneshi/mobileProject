package com.edufire.dic3;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.edufire.dic3.Models.User;
import com.edufire.dic3.Models.Word;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "Dictionary";
    public static final String Table1 = "UserInformation";
    public static final String Table2 = "TeammateRequest";
    public static final String Table3 = "Groups";
    public static final String Table4 = "WordSearch";
    public Context context1;


    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
        context1 = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table UserInformation(username text primary key, password text, score Integer, limitRequestCounter Integer, isPremium INTEGER DEFAULT 0, premiumCode text)");
        sqLiteDatabase.execSQL("create table TeammateRequest(usernameOfSender text, usernameOfReceiver text, primary key (usernameOfSender, usernameOfReceiver))");
        sqLiteDatabase.execSQL("create table Groups(firstUsername text, secondUsername text, primary key (firstUsername, secondUsername))");
        sqLiteDatabase.execSQL("create table WordSearch(user text, word text, meaning1 text, meaning2 text, meaning3 text, audioLink text, description text, examples text, roleOfWordInSentence text, synonyms1 text, synonyms2 text,synonyms3 text, reqHour text, primary key (user, word))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + Table1);
        sqLiteDatabase.execSQL("drop table if exists " + Table2);
        sqLiteDatabase.execSQL("drop table if exists " + Table3);
        sqLiteDatabase.execSQL("drop table if exists " + Table4);


        onCreate(sqLiteDatabase);
    }

    public void insertUserInformation(String username, String password , int score ,int limitRequestCounter, boolean isPremium, String premiumCode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues userValues = new ContentValues();
        userValues.put("username", username);
        userValues.put("password", password);
        userValues.put("score", score);
        userValues.put("limitRequestCounter", limitRequestCounter);
        userValues.put("isPremium", isPremium);
        userValues.put("premiumCode", premiumCode);
        db.insert(Table1, null, userValues);
    }

    public void insertTeammateRequest(String usernameOfSender, String usernameOfReceiver){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues requestValues = new ContentValues();
        requestValues.put("usernameOfSender", usernameOfSender);
        requestValues.put("usernameOfReceiver", usernameOfReceiver);
        db.insert(Table2, null, requestValues);
    }

    public void insertGroups(String firstUsername, String secondUsername){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues groupValues = new ContentValues();
        groupValues.put("firstUsername", firstUsername);
        groupValues.put("secondUsername", secondUsername);
        db.insert(Table3, null, groupValues);
    }

    public void insertWordInformation(String user, String word , String meaning1 , String meaning2 , String meaning3 , String audioLink, String description , String examples, String roleOfWordInSentence, String synonyms1, String synonyms2, String synonyms3, String reqHour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues wordValues = new ContentValues();
        wordValues.put("user", user);
        wordValues.put("word", word);
        wordValues.put("meaning1", meaning1);
        wordValues.put("meaning2", meaning2);
        wordValues.put("meaning3", meaning3);
        wordValues.put("audioLink", audioLink);
        wordValues.put("description", description);
        wordValues.put("examples", examples);
        wordValues.put("roleOfWordInSentence", roleOfWordInSentence);
        wordValues.put("synonyms1", synonyms1);
        wordValues.put("synonyms2", synonyms2);
        wordValues.put("synonyms3", synonyms3);
        wordValues.put("reqHour", reqHour);
        db.insert(Table4, null, wordValues);
    }

    public void deleteData(String user, String password, String word){
        if(getUserSearchWordFromDatabase(user) != null) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM WeatherInfo WHERE user " + "=\"" + user + "\" AND password " + "=\"" + password + "\" AND word " + "=\"" + word + "\";");
        }
    }

    public void deleteTeammateRequest(String usernameOfSender, String usernameOfReceiver){
        SQLiteDatabase db = this.getWritableDatabase();
        if(isTeammateRequestExistsInDatabase(usernameOfSender, usernameOfReceiver)) {
            db.execSQL("DELETE FROM TeammateRequest WHERE usernameOfSender " + "=\"" + usernameOfSender + "\" AND usernameOfReceiver " + "=\"" + usernameOfReceiver + "\";");
        }
        if(isTeammateRequestExistsInDatabase(usernameOfReceiver, usernameOfSender)) {
            db.execSQL("DELETE FROM TeammateRequest WHERE usernameOfSender " + "=\"" + usernameOfReceiver + "\" AND usernameOfReceiver " + "=\"" + usernameOfSender + "\";");
        }
    }

    public ArrayList<Word> getUserSearchWordFromDatabase(String user) {
        ArrayList<Word> UserSearchWords = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table4, null);

        if (cursorCourses.moveToFirst()) {
            do {
                if (cursorCourses.getString(0).equals(user)) {
                    String word = cursorCourses.getString(1);
                    String audioLink = cursorCourses.getString(5);
                    String description = cursorCourses.getString(6);
                    String roleOfWordInSentence = cursorCourses.getString(8);
                    ArrayList<String> examples = new ArrayList<>();
                    examples.add(cursorCourses.getString(7));
                    ArrayList<String> meaning = new ArrayList<>();
                    ArrayList<String> synonyms = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        if(cursorCourses.getString(2 + i) != null)
                            meaning.add(cursorCourses.getString(2 + i));
                        if(cursorCourses.getString(9 + i) != null)
                            synonyms.add(cursorCourses.getString(9 + i));
                    }
                    UserSearchWords.add(new Word(word, meaning, audioLink, description, examples, roleOfWordInSentence, synonyms));
                }
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        if(UserSearchWords.size() == 0)
            return null;
        return UserSearchWords;
    }

    public void getAllUserFromDataBase() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table1, null);

        if (cursorCourses.moveToFirst()) {
            do {
                User user = new User(cursorCourses.getString(0), cursorCourses.getString(1));
                ArrayList<Word> searched = getUserSearchWordFromDatabase(user.getUsername());
                if(searched != null)
                    user.setSearchWord(searched);
                user.setLimitRequestCounter(cursorCourses.getInt(3));
                user.setScore(cursorCourses.getInt(2));
                user.setPremium(cursorCourses.getInt(4));
                user.setPremiumCode(cursorCourses.getString(5));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
    }

    public ArrayList<String> getInvitation(String receiver){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table2, null);

        ArrayList<String> sender = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {
                if(cursorCourses.getString(1).equals(receiver)){
                    sender.add(cursorCourses.getString(0));
                }
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return sender;
    }

    public ArrayList<String> getGroupCommon(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table3, null);

        ArrayList<String> inSameGroup = new ArrayList<>();

        if (cursorCourses.moveToFirst()) {
            do {
                if(cursorCourses.getString(1).equals(username)){
                    inSameGroup.add(cursorCourses.getString(0));
                } else if(cursorCourses.getString(0).equals(username)){
                    inSameGroup.add(cursorCourses.getString(1));
                }
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return inSameGroup;
    }

    public boolean isTeammateRequestExistsInDatabase(String sender, String receiver) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table2, null);

        if (cursorCourses.moveToFirst()) {
            do {
                if(cursorCourses.getString(0).equals(sender) && cursorCourses.getString(1).equals(receiver)){
                    return true;
                }
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return false;
    }

    public boolean IsInSameGroup(String sender, String receiver){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + Table3, null);

        if (cursorCourses.moveToFirst()) {
            do {
                if(cursorCourses.getString(0).equals(sender) && cursorCourses.getString(1).equals(receiver)){
                    return true;
                } else if(cursorCourses.getString(1).equals(sender) && cursorCourses.getString(2).equals(receiver)){
                    return true;
                }
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return false;
    }

    public void updateUserScore(String username, int score,boolean isPremium){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        contentValues.put("isPremium", isPremium);
        db.update(Table1, contentValues, "username = '" + username + "'", null);
    }

}
