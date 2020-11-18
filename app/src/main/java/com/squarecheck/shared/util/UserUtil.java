package com.squarecheck.shared.util;

import android.content.Context;

import com.squarecheck.base.util.UtilInterface;

public class UserUtil extends SharedPreferencesUtil {
    private final static String SHARED_PREFS_NAME = "UserSharedPrefs";

    // Need Empty Constructor for Factory Method
    public UserUtil() {
    }

    public UserUtil(Context context) {
        super(context, SHARED_PREFS_NAME);
    }

    @Override
    public UtilInterface initialize(Context context) {
        return new UserUtil(context);
    }
}
