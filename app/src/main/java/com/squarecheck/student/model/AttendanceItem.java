package com.squarecheck.student.model;

import com.squarecheck.R;
import com.squarecheck.shared.util.DateUtil;

public class AttendanceItem {
    private String day;
    private String date;
    private String time;
    private int color;
    private int background;

    public AttendanceItem(String day, String date, String time, int color) {
        this.day = day;
        this.date = date;
        this.time = time;
        this.color = color;
    }

    public static AttendanceItem fromAttendance(ScheduleModel model) {
        String day = DateUtil.getDay(model.getTime());
        String date = DateUtil.getDate(model.getTime());
        String time = DateUtil.getTime(model.getAttendances().get(0).getTime());
        String status = model.getAttendances().get(0).getStatus();
        int color;
        switch (status) {
            case "hadir":
                color = R.color.hadir;
                break;
            case "ijin":
                color = R.color.ijin;
                break;
            case "alpa":
                color = R.color.alpa;
                break;
            case "terlambat":
                color = R.color.telat;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
        return new AttendanceItem(day, date, time, color);
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

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
