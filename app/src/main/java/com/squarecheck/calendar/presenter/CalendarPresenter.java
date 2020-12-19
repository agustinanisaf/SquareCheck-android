package com.squarecheck.calendar.presenter;

import com.squarecheck.calendar.contract.CalendarContract;
import com.squarecheck.calendar.model.CalendarModel;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryContract;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public class CalendarPresenter implements CalendarContract.Presenter {
    private final CalendarContract.View view;
    private final CalendarContract.Interactor interactor;

    public CalendarPresenter(CalendarContract.View view,
                                              CalendarContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void start() {
        view.initView();
    }

    @Override
    public void requestCalendar() {
        view.startLoading();
        interactor.requestCalendar(new RequestCallback<List<CalendarModel>>() {
            @Override
            public void requestSuccess(List<CalendarModel> data) {
                view.endLoading();
                view.showCalendar(data);
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }
}
