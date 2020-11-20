package com.squarecheck.lecturer.presenter;

import com.squarecheck.lecturer.contract.LecturerDashboardContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.ScheduleModel;

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
        requestSchedules();
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
}
