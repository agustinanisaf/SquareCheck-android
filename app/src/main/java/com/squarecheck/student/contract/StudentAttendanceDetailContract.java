package com.squarecheck.student.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.AttendanceStatusItem;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.AttendanceItem;

import java.util.List;

public interface StudentAttendanceDetailContract {
    interface View extends BaseView<Presenter> {

        void showAttendances(List<AttendanceItem> attendances);

        void showAttendanceStats(List<AttendanceStatusItem> attendanceStats);

        void showTitle(Title title);
    }

    interface Presenter extends BasePresenter {
        void requestAttendances(int subjectId);
    }

    interface Interactor {
        void requestAttendances(int subjectId, RequestCallback<List<ScheduleModel>> callback);
    }
}
