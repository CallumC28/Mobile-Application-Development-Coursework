package com.example.id2101170701;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GolfDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "golf_scores.db";
    private static final int DATABASE_VERSION = 1;

    public GolfDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table to store player names and scores
        db.execSQL("CREATE TABLE players (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed
    }
}

