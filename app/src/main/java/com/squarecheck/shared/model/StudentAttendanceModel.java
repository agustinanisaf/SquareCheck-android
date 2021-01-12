package com.squarecheck.shared.model;

import com.squarecheck.R;

public class StudentAttendanceModel {
    private String status;
    private String name;
    private String nrp;
    private int color;

    public static StudentAttendanceModel fromPresenceModel(PresenceModel presenceModel) {
        StudentAttendanceModel studentAttendanceModel = new StudentAttendanceModel();
        // Capitalize Status String
        String status = presenceModel.getStatus();
        studentAttendanceModel.status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
        studentAttendanceModel.name = presenceModel.getStudent().getName();
        studentAttendanceModel.nrp = presenceModel.getStudent().getNrp();
        switch (presenceModel.getStatus()) {
            case "hadir":
                studentAttendanceModel.color = R.color.hadir;
                break;
            case "izin":
                studentAttendanceModel.color = R.color.ijin;
                break;
            case "terlambat":
                studentAttendanceModel.color = R.color.telat;
                break;
            case "alpa":
                studentAttendanceModel.color = R.color.alpa;
                break;
        }
        return studentAttendanceModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
