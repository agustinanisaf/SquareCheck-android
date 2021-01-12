package com.squarecheck.student.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squarecheck.login.model.User;
import com.squarecheck.shared.model.DepartmentModel;

public class StudentModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nrp")
    @Expose
    private String nrp;
    @SerializedName("department")
    @Expose
    private DepartmentModel department;
    @SerializedName("classroom")
    @Expose
    private ClassroomModel classroom;
    @SerializedName("user")
    @Expose
    private User user;

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

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }

    public ClassroomModel getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomModel classroom) {
        this.classroom = classroom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
