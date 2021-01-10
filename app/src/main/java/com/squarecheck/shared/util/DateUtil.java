package com.squarecheck.shared.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static Locale locale = new Locale("in", "ID");

    public static String getDay(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("EEEE", locale);
        return (date != null) ? dateFormat.format(date) : null;
    }

    public static String getDate(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", locale);
        return (date != null) ? dateFormat.format(date) : null;
    }

    public static String getFullDate(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy", locale);
        return (date != null) ? dateFormat.format(date) : null;
    }

    public static String getTime(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("hh:MM");
        return (date != null) ? dateFormat.format(date) : "-- : --";
    }
}
