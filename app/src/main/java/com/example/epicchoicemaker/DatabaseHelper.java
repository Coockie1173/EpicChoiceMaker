//https://www.youtube.com/watch?v=aQAIMY-HzL8
package com.example.epicchoicemaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String TABLE_NAME = "PastChoicesTable";
    private static final String Col1 = "ID";
    private static final String Col2 = "ChoiceText";
    private static final String Col3 = "ChoiceDate";
    private static final String Col4 = "ChoiceMaker";

    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + Col2 + " TEXT, " + Col3 + " DATE, " + Col4 + " TEXT)";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, item);
        contentValues.put(Col3, String.valueOf(Calendar.getInstance().getTime()));


        contentValues.put(Col4, name);


        long res = db.insert(TABLE_NAME, null, contentValues);

        if(res == -1)
        {
            return false;
        }
        return true;
    }

    public Cursor GetData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(Query, null);
        return data;
    }

    public void ClearData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
