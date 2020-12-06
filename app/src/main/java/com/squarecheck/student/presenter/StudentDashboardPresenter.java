package com.squarecheck.student.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.AttendanceStatusItem;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;

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
    public void attend(Integer scheduleId) {
        interactor.requestAttend(scheduleId, new RequestCallback<PresenceModel>() {
            @Override
            public void requestSuccess(PresenceModel data) {
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
    }

    @Override
    public void logout() {
        interactor.logout();
        view.redirectToLogin();
    }
}
