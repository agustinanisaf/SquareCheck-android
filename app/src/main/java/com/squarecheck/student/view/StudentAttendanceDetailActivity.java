package com.squarecheck.student.view;

import android.content.Intent;

import com.google.gson.Gson;
import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.shared.Constants;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.interactor.StudentAttendanceDetailInteractor;
import com.squarecheck.student.presenter.StudentAttendanceDetailPresenter;

public class StudentAttendanceDetailActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(Constants.SUBJECT_ID, 1);
        Title title = new Gson().fromJson(intent.getStringExtra(Constants.TITLE_ID), Title.class);

        StudentAttendanceDetailFragment currentFragment = new StudentAttendanceDetailFragment(subjectId, title);
        currentFragment.setPresenter(new StudentAttendanceDetailPresenter(currentFragment, new StudentAttendanceDetailInteractor()));
        setCurrentFragment(currentFragment, true);
    }
}