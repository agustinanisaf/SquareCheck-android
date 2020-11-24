package com.squarecheck.lecturer.presenter;

import com.google.gson.Gson;
import com.squarecheck.lecturer.contract.LecturerScheduleActionContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public class LecturerScheduleActionPresenter implements LecturerScheduleActionContract.Presenter {
    private final LecturerScheduleActionContract.View view;
    private final LecturerScheduleActionContract.Interactor interactor;

    public LecturerScheduleActionPresenter(LecturerScheduleActionContract.View view, LecturerScheduleActionContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initView();
    }

    @Override
    public void requestSchedule(Integer scheduleId) {
        view.startLoading();
        interactor.requestSchedule(scheduleId, new RequestCallback<ScheduleModel>() {
            @Override
            public void requestSuccess(ScheduleModel data) {
                view.showSchedule(data);
                view.showAttendances("0", "0");
                processTitle(data);
                requestAttendances(scheduleId);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    private void processTitle(ScheduleModel data) {
        Title title = new Title(data.getSubject().getName(), data.getSubject().getClassroom().getName());
        view.showTitle(title);
        view.setTitle(new Gson().toJson(title, Title.class));
    }

    @Override
    public void openSchedule(int scheduleId) {
        view.startLoading();
        interactor.requestOpenSchedule(scheduleId, new RequestCallback<ScheduleModel>() {
            @Override
            public void requestSuccess(ScheduleModel data) {
                requestAttendances(scheduleId);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void requestAttendances(int scheduleId) {
        interactor.requestAttendances(scheduleId, new RequestCallback<List<PresenceModel>>() {
            @Override
            public void requestSuccess(List<PresenceModel> data) {
                view.endLoading();
                processShowAttendances(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    private void processShowAttendances(List<PresenceModel> data) {
        String presence = String.valueOf(data.stream().filter(c -> c.getStatus().equals("hadir")).count());
        String total = String.valueOf(data.size());
        view.showAttendances(presence, total);
    }

    @Override
    public void removeSchedule(int scheduleId) {
        view.startLoading();
        interactor.requestRemoveSchedule(scheduleId, new RequestCallback<APIResponse>() {
            @Override
            public void requestSuccess(APIResponse data) {
                view.endLoading();
                view.showAttendances("0", "0");
                view.showError(data.getDescription());
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }
}
