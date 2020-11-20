package com.squarecheck.student.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;

import java.util.ArrayList;

public interface StudentDashboardContract {
    interface View extends BaseView<StudentDashboardContract.Presenter> {
        void redirectToAttendanceDetail(String id);
        void showSubjectsList();
        void showDetailProfile();
    }

    interface Presenter extends BasePresenter {
        ArrayList<String> requestSubjectsList();
        void requestDetail();
    }

    interface Interactor {
        void requestSubjectsList();
        void requestDetail();
    }
}
