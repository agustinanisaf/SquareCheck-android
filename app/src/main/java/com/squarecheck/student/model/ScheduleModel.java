package com.squarecheck.student.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class ScheduleModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subject")
    @Expose
    private SubjectModel subject;
    @SerializedName("time")
    @Expose
    private Date time;
    @SerializedName("start_time")
    @Expose
    private Date startTime;
    @SerializedName("end_time")
    @Expose
    private Date endTime;
    @SerializedName("hadir")
    @Expose
    private Integer hadir;
    @SerializedName("izin")
    @Expose
    private Integer izin;
    @SerializedName("terlambat")
    @Expose
    private Integer terlambat;
    @SerializedName("alpa")
    @Expose
    private Integer alpa;
    @SerializedName("attendance")
    @Expose
    private List<PresenceModel> attendances;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getHadir() {
        return hadir;
    }

    public void setHadir(Integer hadir) {
        this.hadir = hadir;
    }

    public Integer getIzin() {
        return izin;
    }

    public void setIzin(Integer izin) {
        this.izin = izin;
    }

    public Integer getTerlambat() {
        return terlambat;
    }

    public void setTerlambat(Integer terlambat) {
        this.terlambat = terlambat;
    }

    public Integer getAlpa() {
        return alpa;
    }

    public void setAlpa(Integer alpa) {
        this.alpa = alpa;
    }

    public List<PresenceModel> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<PresenceModel> attendances) {
        this.attendances = attendances;
    }

    public String getClassSubject() {
        return String.format("%s - %s", subject.getClassroom().getSlug(), subject.getName());
    }
}