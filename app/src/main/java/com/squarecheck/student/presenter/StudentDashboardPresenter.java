package com.squarecheck.student.presenter;

import android.util.Log;
import android.widget.TextView;

import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.model.ScheduleModel;
import com.squarecheck.student.model.StudentModel;
import com.squarecheck.student.model.SubjectModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StudentDashboardPresenter implements StudentDashboardContract.Presenter {
    private final StudentDashboardContract.View view;
    private final StudentDashboardContract.Interactor interactor;

    public StudentDashboardPresenter(StudentDashboardContract.View view, StudentDashboardContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void requestSubjectsList() {
        interactor.requestSubjectsList(new RequestCallback<List<SubjectModel>>() {
            @Override
            public void requestSuccess(List<SubjectModel> data) {
                view.showSubjectsList(data);
            }

            @Override
            public void requestError(String message) {
                Log.d("1", message);
            }
        });
    }

    @Override
    public void requestDetail() {
        interactor.requestDetail(new RequestCallback<StudentModel>() {
            @Override
            public void requestSuccess(StudentModel data) {
                view.showDetailProfile(data);
            }

            @Override
            public void requestError(String message) {
                Log.d("1", message);
            }
        });
        
    }

    @Override
    public void start() {
        view.initView();
        requestDetail();
        requestSubjectsList();
    }
}