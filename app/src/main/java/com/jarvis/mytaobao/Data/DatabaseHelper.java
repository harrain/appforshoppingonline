package com.jarvis.mytaobao.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    //子类必须有该构造函数
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name){
        this(context, name, VERSION);
    }

    public DatabaseHelper(Context context, String name,int version){
        this(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table user(username varchar(20),passwd varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
