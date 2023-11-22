package com.example.wishwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB";
    private static final int VERSION = 1;
    private static final String USER_TABLE = "userTb";
    private static final String ID = "user_id";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    private static final String C_PASSWORD = "confirm_password";
    private static final String EMAIL = "email";
    private static final String NAME = "name";




    public UserDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + USER_TABLE + "( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT NOT NULL, " +
                PASSWORD + " TEXT NOT NULL, " +
                C_PASSWORD + " TEXT NOT NULL, " +
                EMAIL + " TEXT NOT NULL," +
                NAME + " TEXT NOT NULL)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

        onCreate(db);

    }

    public void addUser(UserModel userModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, userModel.username);
        values.put(PASSWORD, userModel.password);
        values.put(C_PASSWORD, userModel.c_password);
        values.put(EMAIL, userModel.email);
        values.put(NAME, userModel.name);

        db.insert(USER_TABLE, null, values);
    }

    public ArrayList<UserModel> getUser(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE, null);

        ArrayList<UserModel> info = new ArrayList<>();

        while(cursor.moveToNext()){

            UserModel userModel = new UserModel();

            userModel.id = cursor.getInt(0);
            userModel.username = cursor.getString(1);
            userModel.password = cursor.getString(2);
         userModel.c_password = cursor.getString(3);
            userModel.email = cursor.getString(4);
            userModel.name = cursor.getString(5);

            info.add(userModel);
        }

        return info;
    }

    public void updateUser(UserModel userModel){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_NAME, userModel.username);
        values.put(PASSWORD, userModel.password);
        values.put(C_PASSWORD, userModel.c_password);
        values.put(EMAIL, userModel.email);
        values.put(NAME, userModel.name);


        db.update(USER_TABLE, values, ID + "=" + userModel.id, null);


    }

    public void deleteUser(UserModel userModel){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(USER_TABLE, ID + " = ? ", new String[]{String.valueOf(userModel.id)});
    }


    public ArrayList<String> getUsername(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE, null);

        ArrayList<String> info = new ArrayList<>();

        while(cursor.moveToNext()){

            String USERNAME;

            USERNAME = cursor.getString(1);
            info.add(USERNAME);
        }

        return info;
    }
}
