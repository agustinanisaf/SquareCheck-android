package com.squarecheck.shared.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.squarecheck.base.util.UtilInterface;
import com.squarecheck.login.model.User;

public class UserUtil extends SharedPreferencesUtil<User> {
    private final static String SHARED_PREFS_NAME = "UserSharedPrefs";
    private final static String SESSION_USER = "SessionUser";

    // Need Empty Constructor for Factory Method
    public UserUtil() {
    }

    public UserUtil(Context context) {
        super(context, SHARED_PREFS_NAME);
    }

    @Override
    public UtilInterface create(Context context) {
        return new UserUtil(context);
    }

    @Override
    User getSessionData() {
        String sessionDataJson = sharedPrefs.getString(SESSION_USER, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, User.class);
        }
        return null;
    }

    @Override
    void setSessionData(User sessionData) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SESSION_USER, new Gson().toJson(sessionData));
        editor.apply();
    }

}
