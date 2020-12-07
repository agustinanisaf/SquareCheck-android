package com.squarecheck.lecturer.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.lecturer.model.LecturerModel;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public interface LecturerDashboardContract {
    interface View extends BaseView<Presenter> {
        void startLoading();

        void endLoading();

        void showError(String errorMessage);

        void showSchedules(List<ScheduleModel> schedules);

        void redirectToLogin();

        void showLogoutConfirmation();
        void showDetailProfile(LecturerModel student);
        void showProfileImage(String imgPath);
    }

    interface Presenter extends BasePresenter {
        void requestSchedules();

        void logout();
        void requestDetail();
        void requestProfileImage();
    }

    interface Interactor {
        void requestSchedules(RequestCallback<List<ScheduleModel>> callback);

        void logout();
        void requestDetail(RequestCallback<LecturerModel> requestCallback);
        void requestProfileImage();
    }
}
