package com.squarecheck.shared;

import android.app.Application;

import com.squarecheck.shared.util.SquareCheckUtilProvider;

public class SquareCheckApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SquareCheckUtilProvider.initialize(getApplicationContext());
    }
}
