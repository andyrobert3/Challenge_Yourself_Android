package com.example.aaryam123.everyday_challenge_final.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexa on 1/28/2018.
 */

public class ChallengesHelper extends SQLiteOpenHelper{

    public static final String TABLE_CHALLENGES = "CHALLENGES";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_CHALLENGES = "CHALLENGE";

    private static final String DB_NAME = "challenges.db";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE =
            "CREATE TABLE" + TABLE_CHALLENGES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_CHALLENGES + "TEXT)";

    public ChallengesHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
