package com.squarecheck.lecturer.presenter;

import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.ScheduleModel;

import java.util.List;

public class LecturerAttendanceSummaryPresenter implements LecturerAttendanceSummaryContract.Presenter {
    private final LecturerAttendanceSummaryContract.View view;
    private final LecturerAttendanceSummaryContract.Interactor interactor;

    public LecturerAttendanceSummaryPresenter(LecturerAttendanceSummaryContract.View view,
                                              LecturerAttendanceSummaryContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initView();
    }

    @Override
    public void requestSubjectAttendances(int subjectId) {
        view.startLoading();
        interactor.requestSubjectAttendances(subjectId, new RequestCallback<List<ScheduleModel>>() {
            @Override
            public void requestSuccess(List<ScheduleModel> data) {
                view.endLoading();
                view.showSubjectAttendances(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }
}
