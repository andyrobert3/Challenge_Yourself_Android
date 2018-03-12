package com.example.aaryam123.everyday_challenge_final.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alexa on 1/28/2018.
 */

public class ChallengesDataSource {

    SQLiteDatabase mDatabase;
    private ChallengesHelper mChallengesHelper;
    private Context mContext;

    public ChallengesDataSource(Context context) {
        mContext = context;
        mChallengesHelper = new ChallengesHelper(context);
    }

    // Open database
    public void open() throws SQLException {
        mDatabase = mChallengesHelper.getWritableDatabase();
        // creates database if doesn't exists
        // else opens database
    }

    // close database
    public void close() {
        mDatabase.close();
    }

    // insert

    // select

    // update

    // delete
}
