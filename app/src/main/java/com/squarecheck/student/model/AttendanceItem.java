package com.squarecheck.student.model;

public class AttendanceItem {
    private String day;
    private String date;
    private String time;
    private int color;

    public AttendanceItem(String day, String date, String time, int color) {
        this.day = day;
        this.date = date;
        this.time = time;
        this.color = color;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
