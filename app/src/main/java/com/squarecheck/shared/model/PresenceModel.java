package com.squarecheck.shared.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squarecheck.student.model.StudentModel;

import java.util.Date;

public class PresenceModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private Date time;
    @SerializedName("student")
    @Expose
    private StudentModel student;

    public PresenceModel(String status, Date time) {
        this.status = status;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }
}
