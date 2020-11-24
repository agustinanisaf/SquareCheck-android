package com.squarecheck.student.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.contract.StudentDashboardContract;
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
    public void start() {
        view.initView();
        requestDetail();
        requestSubjectsList();
    }

    @Override
    public void logout() {
        interactor.logout();
        view.redirectToLogin();
    }
}
