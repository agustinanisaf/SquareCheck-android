package com.squarecheck.lecturer.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.AttendanceStatusItem;
import com.squarecheck.shared.model.PresenceModel;
import com.squarecheck.shared.model.Title;

import java.util.List;

public interface LecturerAttendanceSummaryDetailContract {
    interface View extends BaseView<Presenter> {
        void showStudentAttendances(List<PresenceModel> data);

        void showTitle(Title title);

        void showAttendanceStats(List<AttendanceStatusItem> attendanceStats);
    }

    interface Presenter extends BasePresenter {
        void requestStudentAttendances(int studentId);
    }

    interface Interactor {
        void requestStudentAttendances(int studentId, RequestCallback<List<PresenceModel>> callback);
    }
}
