package com.squarecheck.student.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;

public class StudentAttendanceNotificationContract {
    public interface View extends BaseView<Presenter> {
        void backToDashboard();
    }

    public interface Presenter extends BasePresenter {

    }
}
