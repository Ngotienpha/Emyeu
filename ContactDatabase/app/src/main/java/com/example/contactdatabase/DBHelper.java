package com.example.contactdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "Userdata.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Information(name TEXT primary key, dob TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Information");
    }

    public Boolean insertuserdata(String name, String dob, String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("dob",dob);
        contentValues.put("email",email);
        long result = DB.insert("Information", null, contentValues);
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Information",null);
        return cursor;
    }
}
