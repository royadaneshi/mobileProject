package com.edufire.dic3;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "UserAndSearchWords";
    public static final String TableName = "searchWords";
    public Context context1;


    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
        context1 = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table searchWords(user text,password text, word text, meaning1 text, meaning2 text, meaning3 text, audioLink text, description text, examples text, roleOfWordInSentence text, synonyms1 text, synonyms2 text,synonyms3 text, reqHour text, primary key (user, password, word))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists searchWords");
        onCreate(sqLiteDatabase);
    }

    public void insertData(String user, String password , String word , String meaning1 , String meaning2 , String meaning3 , String audioLink, String description , String examples, String roleOfWordInSentence, String synonyms1, String synonyms2, String synonyms3, String reqHour) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("password", password);
        contentValues.put("word", word);
        contentValues.put("meaning1", meaning1);
        contentValues.put("meaning2", meaning2);
        contentValues.put("meaning3", meaning3);
        contentValues.put("audioLink", audioLink);
        contentValues.put("description", description);
        contentValues.put("examples", examples);
        contentValues.put("roleOfWordInSentence", roleOfWordInSentence);
        contentValues.put("synonyms1", synonyms1);
        contentValues.put("synonyms2", synonyms2);
        contentValues.put("synonyms3", synonyms3);
        contentValues.put("reqHour", reqHour);
        db.insert(TableName, null, contentValues);
    }

    public void deleteData(String user, String password, String word){
        if(getDataFromDataBase(user, password, word) != null) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM WeatherInfo WHERE user " + "=\"" + user + "\" AND password " + "=\"" + password + "\" AND word " + "=\"" + word + "\";");
        }
    }

    public ArrayList<String> getDataFromDataBase(String user, String password, String word) {
        ArrayList<String> UserSearchWords = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("select * from searchWords", null);

        if (cursorCourses.moveToFirst()) {
            do {
                if (cursorCourses.getString(0).equals(user) &&
                        cursorCourses.getString(1).equals(password) &&
                        cursorCourses.getString(2).equals(word)) {
                    UserSearchWords.add(cursorCourses.getString(2));
                    UserSearchWords.add(cursorCourses.getString(3));
                    UserSearchWords.add(cursorCourses.getString(4));
                    UserSearchWords.add(cursorCourses.getString(5));
                    UserSearchWords.add(cursorCourses.getString(6));
                    UserSearchWords.add(cursorCourses.getString(7));
                    UserSearchWords.add(cursorCourses.getString(8));
                    UserSearchWords.add(cursorCourses.getString(9));
                    UserSearchWords.add(cursorCourses.getString(10));
                    UserSearchWords.add(cursorCourses.getString(11));
                    UserSearchWords.add(cursorCourses.getString(12));
                    UserSearchWords.add(cursorCourses.getString(13));
                    return UserSearchWords;
                }
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();

        return null;
    }

}
