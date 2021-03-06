package com.squarecheck.student.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.AttendanceStatusItem;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.model.SubjectModel;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.NotificationPresenceItem;
import com.squarecheck.student.model.StudentModel;

import java.util.List;

public class StudentDashboardPresenter implements StudentDashboardContract.Presenter {
    private final StudentDashboardContract.View view;
    private final StudentDashboardContract.Interactor interactor;

    public StudentDashboardPresenter(StudentDashboardContract.View view, StudentDashboardContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestSubjectsList() {
        interactor.requestSubjectsList(new RequestCallback<List<SubjectModel>>() {
            @Override
            public void requestSuccess(List<SubjectModel> data) {
                view.showSubjectsList(data);
            }

            @Override
            public void requestError(String message) {
                Log.d("1", message);
            }
        });
    }

    @Override
    public void requestCurrentSchedule() {
        interactor.requestCurrentSchedule(new RequestCallback<ScheduleModel>() {
            @Override
            public void requestSuccess(ScheduleModel data) {
                view.showCurrentSchedule(data);
            }

            @Override
            public void requestError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void requestAttendanceStats() {
        interactor.requestAttendanceStats(new RequestCallback<List<AttendanceStatusItem>>() {
            @Override
            public void requestSuccess(List<AttendanceStatusItem> data) {
                view.showAttendanceStats(data);
            }

            @Override
            public void requestError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void requestDetail() {
        interactor.requestDetail(new RequestCallback<StudentModel>() {
            @Override
            public void requestSuccess(StudentModel data) {
                view.showDetailProfile(data);
            }

            @Override
            public void requestError(String message) {
                Log.d("1", message);
            }
        });
    }

    @Override
    public String showNextTitle(SubjectModel subject) {
        return new Gson().toJson(new Title(subject.getName(), subject.getLecturer().getName()));
    }

    @Override
    public void attend(ScheduleModel schedule) {
        interactor.requestAttend(schedule, new RequestCallback<NotificationPresenceItem>() {
            @Override
            public void requestSuccess(NotificationPresenceItem data) {
                view.redirectToNotificationSuccess(data);
            }

            @Override
            public void requestError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void start() {
        view.initView();
        requestDetail();
        requestAttendanceStats();
        requestCurrentSchedule();
        requestSubjectsList();
        requestProfileImage();
    }

    @Override
    public void logout() {
        interactor.logout();
        view.redirectToLogin();
    }

    @Override
    public void requestProfileImage() {
        String imgURL = "";
        interactor.requestProfileImage();
        view.showProfileImage(imgURL);
    }
}
