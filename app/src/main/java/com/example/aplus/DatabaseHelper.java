package com.example.aplus;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="APlusone.db";
    public static final String TABLE_1="parent";
    public static final String TABLE_2="student";
    public static final String TABLE_3="teacher";
    //public static final String TABLE_4="Class2Sci";



    DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String table1="CREATE TABLE " + TABLE_1+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,ChildsName TEXT,Phone TEXT,Gmail TEXT,UserName TEXT,Password TEXT)";
        String table2="CREATE TABLE " + TABLE_2+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,Class TEXT,Phone TEXT,Email TEXT,UserName TEXT,Password TEXT)";
        String table3="CREATE TABLE " + TABLE_3+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,Class TEXT,Subject TEXT,Phone TEXT,Email TEXT,UserName TEXT,Password TEXT)";
        //String table4="CREATE TABLE " + TABLE_4+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
        //db.execSQL((table4));
        db.execSQL(table1);
    db.execSQL(table2);
    db.execSQL(table3);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {


        db.execSQL("DROP TABLE IF EXISTS " +TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_2);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_3);

       //db.execSQL("DROP TABLE IF EXISTS " +TABLE_4);
        onCreate(db);
    }




}
