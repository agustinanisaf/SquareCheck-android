package com.squarecheck.student.presenter;

import com.squarecheck.student.contract.StudentAttendanceNotificationContract;

public class StudentAttendanceNotificationPresenter implements StudentAttendanceNotificationContract.Presenter {
    private final StudentAttendanceNotificationContract.View view;

    public StudentAttendanceNotificationPresenter(StudentAttendanceNotificationContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.initView();
    }
}
