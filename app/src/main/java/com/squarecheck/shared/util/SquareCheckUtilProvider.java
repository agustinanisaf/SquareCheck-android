package com.squarecheck.shared.util;

import android.content.Context;

import com.squarecheck.base.util.UtilProvider;

public class SquareCheckUtilProvider extends UtilProvider {
    public static void initialize(Context context) {
        UtilProvider.initialize(
                context,
                UserUtil.class
        );
    }
}
