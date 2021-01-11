package com.squarecheck.lecturer.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public interface LecturerScheduleActionContract {
    interface View extends BaseView<Presenter> {
        void showSchedule(ScheduleModel schedule);

        void showAttendances(String presence, String total);

        void showTitle(Title title);

        void setTitle(String title);

        void changeButtonToClose();

        void disableOpenButton();

        void enableOpenButton();
    }

    interface Presenter extends BasePresenter {
        void requestSchedule(Integer scheduleId);

        void openSchedule(int scheduleId);

        void closeSchedule(int scheduleId);

        void requestAttendances(int scheduleId);

        void removeSchedule(int scheduleId);
    }

    interface Interactor {
        void requestSchedule(Integer scheduleId, RequestCallback<ScheduleModel> callback);

        void requestOpenSchedule(int scheduleId, RequestCallback<ScheduleModel> callback);

        void requestCloseSchedule(int scheduleId, RequestCallback<ScheduleModel> callback);

        void requestRemoveSchedule(int scheduleId, RequestCallback<APIResponse> callback);

        void requestAttendances(int scheduleId, RequestCallback<List<PresenceModel>> callback);
    }
}
