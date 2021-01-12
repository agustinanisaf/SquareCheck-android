package com.squarecheck.shared.model;

public class AttendanceStatusItem {
    private String total;
    private String status;
    private int color;

    public AttendanceStatusItem(String total, String status, int color) {
        this.total = total;
        this.status = status;
        this.color = color;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
