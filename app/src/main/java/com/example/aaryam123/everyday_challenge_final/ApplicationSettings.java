package com.example.aaryam123.everyday_challenge_final;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by alexa on 1/28/2018.
 */

public class ApplicationSettings {
    SharedPreferences mSharedPreferences;

    public ApplicationSettings(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getStoragePreference() {
        return mSharedPreferences.getString("Storage", "INTERNAL");
    }

    // save the preferences
    public void setSharedPreferences(String storageType) {
        mSharedPreferences
                .edit()
                .putString("Storage", storageType)
                .apply();
    }
}
