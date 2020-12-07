package com.squarecheck.student.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.AttendanceStatusItem;
import com.squarecheck.student.model.NotificationPresenceItem;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;

import java.util.List;

public interface StudentDashboardContract {
    interface View extends BaseView<StudentDashboardContract.Presenter> {
        void startLoading();

        void endLoading();

        void showError(String errorMessage);

        void redirectToAttendanceDetail(SubjectModel subject);

        void showSubjectsList(List<SubjectModel> SubjectsList);

        void showCurrentSchedule(ScheduleModel schedule);

        void showAttendanceStats(List<AttendanceStatusItem> attendanceStatusItems);

        void showDetailProfile(StudentModel student);

        void showLogoutConfirmation();

        void redirectToLogin();

        void redirectToNotificationSuccess(NotificationPresenceItem data);

        void showProfileImage(String imgPath);
    }

    interface Presenter extends BasePresenter {
        void requestSubjectsList();

        void requestCurrentSchedule();

        void requestAttendanceStats();

        void requestDetail();

        void logout();

        String showNextTitle(SubjectModel subject);

        void attend(ScheduleModel schedule);

        void requestProfileImage();
    }

    interface Interactor {
        void requestSubjectsList(RequestCallback<List<SubjectModel>> requestCallback);

        void requestCurrentSchedule(RequestCallback<ScheduleModel> requestCallback);

        void requestAttend(ScheduleModel schedule, RequestCallback<NotificationPresenceItem> requestCallback);

        void requestAttendanceStats(RequestCallback<List<AttendanceStatusItem>> requestCallback);

        void requestDetail(RequestCallback<StudentModel> requestCallback);

        void logout();

        void requestProfileImage();
    }
}
