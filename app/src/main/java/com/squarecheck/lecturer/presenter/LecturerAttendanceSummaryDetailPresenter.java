package com.squarecheck.lecturer.presenter;

import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryContract;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryDetailContract;
import com.squarecheck.lecturer.interactor.LecturerAttendanceSummaryDetailInteractor;
import com.squarecheck.lecturer.view.LecturerAttendanceSummaryDetailFragment;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public class LecturerAttendanceSummaryDetailPresenter implements LecturerAttendanceSummaryDetailContract.Presenter {
    private final LecturerAttendanceSummaryDetailContract.View view;
    private final LecturerAttendanceSummaryDetailContract.Interactor interactor;

    public LecturerAttendanceSummaryDetailPresenter(LecturerAttendanceSummaryDetailContract.View view,
                                              LecturerAttendanceSummaryDetailContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initView();
    }

    @Override
    public void requestStudentAttendances(int scheduleId) {
        view.startLoading();
        interactor.requestStudentAttendances(scheduleId, new RequestCallback<List<PresenceModel>>() {
            @Override
            public void requestSuccess(List<PresenceModel> data) {
                view.endLoading();
                view.showStudentAttendances(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }
}
