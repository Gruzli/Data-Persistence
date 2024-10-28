package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDAO {
    //data access object
    static String Table_MsUser = "MsUser";
    static String Field_MsUser_Username = "username";
    static String Field_MsUser_Password = "password";

    public void createMsUser(SQLiteDatabase db){
        String qCreate = "";
        qCreate = "Create table if not exists '"+Table_MsUser+"' (\n"+"'"+Field_MsUser_Username+"' Text,\n"+"'"+Field_MsUser_Password+"' Text);";
        db.execSQL(qCreate);
    }

    public void addRowMsUser(Context ctx,User user){
        MySqliteHelper helper = new MySqliteHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Field_MsUser_Username,user.username);
        cv.put(Field_MsUser_Password,user.password);

        db.insertWithOnConflict(Table_MsUser,null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    public static User getFirstUser(Context ctx){
        ArrayList<User> resultUser = new ArrayList<>();
        MySqliteHelper helper = new MySqliteHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        String selectionString = null;
        String[] selectionArgs = null;

        Cursor resultCursor = db.query(Table_MsUser, null, null, null, null, null, null);
        while (resultCursor.moveToNext()){
            String username = resultCursor.getString(0);
            String password = resultCursor.getString(1);
            resultUser.add(new User(username,password));
        }
        return resultUser.get(0);
    }
}
