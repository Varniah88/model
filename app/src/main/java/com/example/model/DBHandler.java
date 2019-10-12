package com.example.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context) {
        super(context, "user1_db.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + UserProfile.Users.TABLE_NAME + "( " + UserProfile.Users.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT ," + UserProfile.Users.COL_2 + " TEXT," +
                UserProfile.Users.COL_3 + " TEXT, " + UserProfile.Users.COL_4 + " TEXT, " + UserProfile.Users.COL_5 + " TEXT" + ")";


        Log.d("createQuery", createQuery);

        try {
            db.execSQL(createQuery);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String createQuery = "CREATE TABLE " + UserProfile.Users.TABLE_NAME + "(" + UserProfile.Users.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " + UserProfile.Users.COL_2 + "TEXT ," + UserProfile.Users.COL_3 + "TEXT ," + UserProfile.Users.COL_4 + "TEXT ," + UserProfile.Users.COL_5 + "TEXT " + ")";

        Log.d("createQuery", createQuery);

        try {
            db.execSQL(createQuery);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }

    }

    public boolean addinfo(UserProfile.Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        String insertQuery = "INSERT INTO " + UserProfile.Users.TABLE_NAME + "(" + UserProfile.Users.COL_2 + "," + UserProfile.Users.COL_3 + "," + UserProfile.Users.COL_4 + "," +
                UserProfile.Users.COL_5 + ") VALUES('" + users.getUsername() + "','" + users.getDob() + "','" + users.getPswrd() + "','" + users.getGender() + "')";

        Log.d("insertQuery", insertQuery);

        try {
            db.execSQL(insertQuery);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
        }
        db.close();
        return false;


    }

    public UserProfile.Users readallinfo(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();

        String readsinflequery = "SELECT * FROM " + UserProfile.Users.TABLE_NAME + " WHERE " + UserProfile.Users.COL_2 + "= '" + userName + "'";
        Cursor cursor = db.rawQuery(readsinflequery,null);

        if(cursor.moveToFirst()){
            UserProfile.Users users = UserProfile.getprofile().getUser();

            users.setId(Integer.parseInt(cursor.getString(0)));
            users.setUsername(cursor.getString(1));
            users.setDob(cursor.getString(2));
            users.setPswrd(cursor.getString(3));
            users.setGender(cursor.getString(4));

            return users;
        }

        return null;

    }

    public UserProfile.Users readallinfo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String readsinflequery = "SELECT * FROM " + UserProfile.Users.TABLE_NAME + " WHERE " + UserProfile.Users.COL_1 + "= '" + id + "'";
        Cursor cursor = db.rawQuery(readsinflequery,null);

        if(cursor.moveToFirst()){
            UserProfile.Users users = UserProfile.getprofile().getUser();

            users.setId(Integer.parseInt(cursor.getString(0)));
            users.setUsername(cursor.getString(1));
            users.setDob(cursor.getString(2));
            users.setPswrd(cursor.getString(3));
            users.setGender(cursor.getString(4));

            return users;
        }

        return null;

    }

    public void deleteinfo(String username){

        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM "+UserProfile.Users.TABLE_NAME+" WHERE "+UserProfile.Users.COL_2+" = '"+username+"' ";

        Log.d("deleteQuery",deleteQuery);
        db.execSQL(deleteQuery);
        db.close();

    }

    public boolean updateinfo(UserProfile.Users users){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        String username = users.getUsername();
        String Dob = users.getDob();
        String password =users.getPswrd();
        String gender = users.getGender();
        int id = users.getId();

        contentValues.put(UserProfile.Users.COL_2,username);
        contentValues.put(UserProfile.Users.COL_3,Dob);
        contentValues.put(UserProfile.Users.COL_4,password);
        contentValues.put(UserProfile.Users.COL_5,gender);


        int results = db.update(UserProfile.Users.TABLE_NAME,contentValues,UserProfile.Users.COL_1+" =?",new  String[]{String.valueOf(id)});

        if (results > 0)
            return true;
        else
            return false;
    }


}
