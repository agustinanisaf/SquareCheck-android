package com.squarecheck.student.presenter;

import com.squarecheck.R;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.contract.StudentAttendanceDetailContract;
import com.squarecheck.student.model.AttendanceItem;
import com.squarecheck.student.model.AttendanceStatusItem;
import com.squarecheck.student.model.ScheduleModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentAttendanceDetailPresenter implements StudentAttendanceDetailContract.Presenter {
    private final StudentAttendanceDetailContract.View view;
    private final StudentAttendanceDetailContract.Interactor interactor;

    public StudentAttendanceDetailPresenter(StudentAttendanceDetailContract.View view,
                                            StudentAttendanceDetailContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initView();
    }

    @Override
    public void requestAttendances(int subjectId) {
        view.startLoading();
        interactor.requestAttendances(subjectId, new RequestCallback<List<ScheduleModel>>() {
            @Override
            public void requestSuccess(List<ScheduleModel> data) {
                view.endLoading();
                processAttendances(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    private void processAttendances(List<ScheduleModel> data) {
        List<AttendanceItem> attendances = data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0).map(AttendanceItem::fromAttendance).collect(Collectors.toList());
        AttendanceStatusItem presenceStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("hadir")).count()), "Hadir", R.color.hadir);
        AttendanceStatusItem excuseStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("izin")).count()), "Izin", R.color.ijin);
        AttendanceStatusItem lateStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("terlambat")).count()), "Terlambat", R.color.telat);
        AttendanceStatusItem absentStat = new AttendanceStatusItem(String.valueOf(data.stream().filter(d -> d.getAttendances() != null && d.getAttendances().size() != 0 && d.getAttendances().get(0).getStatus().equals("alpa")).count()), "Alpa", R.color.alpa);
        List<AttendanceStatusItem> attendanceStats = Arrays.asList(presenceStat, excuseStat, lateStat, absentStat);
        view.showAttendances(attendances);
        view.showAttendanceStats(attendanceStats);
    }
}
