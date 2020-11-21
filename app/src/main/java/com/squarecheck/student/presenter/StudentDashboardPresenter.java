package com.squarecheck.student.presenter;

import android.widget.TextView;

import com.squarecheck.student.contract.StudentDashboardContract;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentDashboardPresenter implements StudentDashboardContract.Presenter {
    private final StudentDashboardContract.View view;
    private final StudentDashboardContract.Interactor interactor;

    public StudentDashboardPresenter(StudentDashboardContract.View view, StudentDashboardContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public ArrayList<String> requestSubjectsList() {
        interactor.requestSubjectsList();
    }

    @Override
    public void requestDetail() {
//        String studentName = view.findView;
//        String nrp = view.findView;
//        String major = view.findView;

        //set text here
        
    }

    @Override
    public void start() {

    }
}
