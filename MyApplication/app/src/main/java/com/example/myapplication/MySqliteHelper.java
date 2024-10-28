package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {
    static String dbName = "dbs";
    static int dbversion = 1;
    public MySqliteHelper(@Nullable Context context) {
        super(context, dbName, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserDAO userDAO = new UserDAO();
        userDAO.createMsUser(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
