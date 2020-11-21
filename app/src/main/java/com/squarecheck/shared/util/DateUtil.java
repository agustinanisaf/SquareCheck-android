package com.squarecheck.shared.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDay(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("E");
        return (date != null) ? dateFormat.format(date) : null;
    }

    public static String getDate(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        return (date != null) ? dateFormat.format(date) : null;
    }
}
