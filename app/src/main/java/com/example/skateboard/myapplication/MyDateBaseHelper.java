package com.example.skateboard.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by skateboard on 16-1-28.
 */
public class MyDateBaseHelper extends SQLiteOpenHelper {



    private String create_table="CREATE TABLE mytable(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT ,score INT );";

    private static int version=1;
    public MyDateBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MyDateBaseHelper(Context context,String name)
    {
        this(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
