package com.squarecheck.shared.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.student.model.ClassroomModel;

public class SubjectModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lecturer")
    @Expose
    private LecturerModel lecturer;
    @SerializedName("classroom")
    @Expose
    private ClassroomModel classroom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LecturerModel getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerModel lecturer) {
        this.lecturer = lecturer;
    }

    public ClassroomModel getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomModel classroom) {
        this.classroom = classroom;
    }
}