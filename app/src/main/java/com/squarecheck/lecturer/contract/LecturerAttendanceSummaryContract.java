package com.squarecheck.lecturer.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public interface LecturerAttendanceSummaryContract {
    interface View extends BaseView<Presenter> {
        void startLoading();

        void endLoading();

        void showError(String errorMessage);

        void showSubjectAttendances(List<ScheduleModel> data);

        void showTitle(Title title);
    }

    interface Presenter extends BasePresenter {
        void requestSubjectAttendances(int subjectId);
    }

    interface Interactor {
        void requestSubjectAttendances(int subjectId, RequestCallback<List<ScheduleModel>> callback);
    }
}
