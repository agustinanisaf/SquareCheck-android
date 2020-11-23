package com.squarecheck.student.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;

import java.util.List;

public interface StudentDashboardContract {
    interface View extends BaseView<StudentDashboardContract.Presenter> {
        void redirectToAttendanceDetail(int subjectId);

        void showSubjectsList(List<SubjectModel> SubjectsList);

        void showDetailProfile(StudentModel student);
    }

    interface Presenter extends BasePresenter {
        void requestSubjectsList();
        void requestDetail();
    }

    interface Interactor {
        void requestSubjectsList(RequestCallback<List<SubjectModel>> requestCallback);
        void requestDetail(RequestCallback<StudentModel> requestCallback);
    }
}
