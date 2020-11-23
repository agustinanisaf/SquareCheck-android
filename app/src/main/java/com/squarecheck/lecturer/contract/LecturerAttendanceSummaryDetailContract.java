package com.squarecheck.lecturer.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.StudentModel;

import java.util.List;

public interface LecturerAttendanceSummaryDetailContract {
    interface View extends BaseView<LecturerAttendanceSummaryDetailContract.Presenter> {
        void startLoading();

        void endLoading();

        void showError(String errorMessage);

        void showStudentAttendances(List<PresenceModel> data);

        void showTitle(Title title);
    }

    interface Presenter extends BasePresenter {
        void requestStudentAttendances(int studentId);
    }

    interface Interactor {
        void requestStudentAttendances(int studentId, RequestCallback<List<PresenceModel>> callback);
    }
}
