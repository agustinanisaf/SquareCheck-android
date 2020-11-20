package com.squarecheck.shared.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.squarecheck.base.util.UtilInterface;

public abstract class SharedPreferencesUtil<T> implements UtilInterface {
    protected SharedPreferences sharedPrefs;

    // Need Empty Constructor for Factory Method
    public SharedPreferencesUtil() {
    }

    public SharedPreferencesUtil(Context context, String SharedPrefsName) {
        this.sharedPrefs = context.getSharedPreferences(SharedPrefsName, Context.MODE_PRIVATE);
    }

    T initialize(T sessionData) {
        setSessionData(sessionData);

        return getSessionData();
    }

    abstract T getSessionData();

    abstract void setSessionData(T sessionData);

    void destroy() {
        sharedPrefs.edit().clear().apply();
    }

    void update(T sessionData) {
        destroy();
        setSessionData(sessionData);
    }
}
