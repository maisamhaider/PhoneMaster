package com.example.phonemaster.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class Db extends SQLiteOpenHelper {

    private static final String databaseName = "my_db";
    private static final String tableName = "numbers_table";
    private static final String id = "id";
    private static final String phoneNumber = "phone_number";


    public Db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table numbers_table" + "(id INTEGER PRIMARY KEY AUTOINCREMENT,phone_number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS numbers_table");
        onCreate(db);
    }
    public boolean insertPhone(List<String> phoneNum) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i=0; i<phoneNum.size(); i++)
        {
            values.put(phoneNumber,phoneNum.get(i));
        }


        long isInsert = db.insert(tableName, null, values);
        return isInsert != -1;
    }

}
