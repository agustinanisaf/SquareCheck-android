package com.squarecheck.lecturer.presenter;

import android.util.Log;

import com.squarecheck.lecturer.contract.LecturerDashboardContract;
import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.ScheduleModel;

import java.util.List;

public class LecturerDashboardPresenter implements LecturerDashboardContract.Presenter {

    private final LecturerDashboardContract.View view;
    private final LecturerDashboardContract.Interactor interactor;

    public LecturerDashboardPresenter(LecturerDashboardContract.View view, LecturerDashboardContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initView();
        requestDetail();
        requestSchedules();
        requestProfileImage();
    }

    @Override
    public void requestSchedules() {
        view.startLoading();
        interactor.requestSchedules(new RequestCallback<List<ScheduleModel>>() {
            @Override
            public void requestSuccess(List<ScheduleModel> data) {
                view.endLoading();
                view.showSchedules(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void logout() {
        interactor.logout();
        view.redirectToLogin();
    }

    @Override
    public void requestDetail() {
        interactor.requestDetail(new RequestCallback<LecturerModel>() {
            @Override
            public void requestSuccess(LecturerModel data) {
                view.showDetailProfile(data);
            }

            @Override
            public void requestError(String message) {
                Log.d("1", message);
            }
        });
    }

    @Override
    public void requestProfileImage() {
        String imgURL = "";
        interactor.requestProfileImage();
        view.showProfileImage(imgURL);
    }
}
