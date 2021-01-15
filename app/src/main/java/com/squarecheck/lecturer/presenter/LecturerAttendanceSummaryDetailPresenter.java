package com.squarecheck.lecturer.presenter;

import com.squarecheck.R;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryDetailContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.AttendanceStatusItem;
import com.squarecheck.shared.model.PresenceModel;

import java.util.Arrays;
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
                processPresences(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    private void processPresences(List<PresenceModel> data) {
        AttendanceStatusItem presenceStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getStatus().equals("hadir")).count()), "Hadir", R.color.hadir);
        AttendanceStatusItem excuseStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getStatus().equals("izin")).count()), "Izin", R.color.ijin);
        AttendanceStatusItem lateStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getStatus().equals("terlambat")).count()), "Terlambat", R.color.telat);
        AttendanceStatusItem absentStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getStatus().equals("alpa")).count()), "Alpa", R.color.alpa);
        List<AttendanceStatusItem> attendanceStats = Arrays.asList(presenceStat, excuseStat, lateStat, absentStat);
        view.showAttendanceStats(attendanceStats);
    }
}
