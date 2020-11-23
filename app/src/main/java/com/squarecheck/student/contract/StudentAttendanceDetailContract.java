package com.squarecheck.student.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.AttendanceItem;
import com.squarecheck.student.model.AttendanceStatusItem;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public interface StudentAttendanceDetailContract {
    interface View extends BaseView<Presenter> {
        void startLoading();

        void endLoading();

        void showError(String message);

        void showAttendances(List<AttendanceItem> attendances);

        void showAttendanceStats(List<AttendanceStatusItem> attendanceStats);
    }

    interface Presenter extends BasePresenter {
        void requestAttendances(int subjectId);
    }

    interface Interactor {
        void requestAttendances(int subjectId, RequestCallback<List<ScheduleModel>> callback);
    }
}
