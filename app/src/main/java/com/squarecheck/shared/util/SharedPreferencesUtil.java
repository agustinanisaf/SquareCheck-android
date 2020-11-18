package com.squarecheck.shared.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.squarecheck.base.util.UtilInterface;

public abstract class SharedPreferencesUtil implements UtilInterface {
    protected SharedPreferences sharedPrefs;

    // Need Empty Constructor for Factory Method
    public SharedPreferencesUtil() {
    }

    public SharedPreferencesUtil(Context context, String SharedPrefsName) {
        this.sharedPrefs = context.getSharedPreferences(SharedPrefsName, Context.MODE_PRIVATE);
    }
}
