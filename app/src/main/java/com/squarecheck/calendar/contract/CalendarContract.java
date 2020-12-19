package com.squarecheck.calendar.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.calendar.model.CalendarModel;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.model.Title;

import java.util.List;

public interface CalendarContract {
    interface View extends BaseView<CalendarContract.Presenter> {
        void startLoading();

        void endLoading();

        void showError(String errorMessage);

        void showCalendar(List<CalendarModel> data);

        void showTitle(Title title);
    }

    interface Presenter extends BasePresenter {
        void requestCalendar();
    }

    interface Interactor {
        void requestCalendar(RequestCallback<List<CalendarModel>> callback);
    }
}
