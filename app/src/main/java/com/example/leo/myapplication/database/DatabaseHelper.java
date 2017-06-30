package com.example.leo.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.leo.myapplication.entity.Animal;

/**
 * Created by leo on 24/06/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;
    private static SQLiteDatabase writableDatabase;
    private static SQLiteDatabase readableDatabase;

    private static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "android_zoo";

    private static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public static synchronized SQLiteDatabase getWritableDatabase(Context context){
        if(writableDatabase == null || !writableDatabase.isOpen()){
            writableDatabase = getInstance(context).getWritableDatabase();
        }
        return writableDatabase;
    }

    public static synchronized SQLiteDatabase getReadableDatabase(Context context){
        if(readableDatabase == null || !readableDatabase.isOpen()){
            readableDatabase = getInstance(context).getReadableDatabase();
        }
        return readableDatabase;
    }

    public static void close(Context context){
        getInstance(context).close();
    }

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void createTables(SQLiteDatabase db){
        db.execSQL(Animal.getDDl());
    }
}
