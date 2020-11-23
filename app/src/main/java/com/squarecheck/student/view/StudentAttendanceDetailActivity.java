package com.squarecheck.student.view;

import android.content.Intent;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.student.interactor.StudentAttendanceDetailInteractor;
import com.squarecheck.student.presenter.StudentAttendanceDetailPresenter;

import static com.squarecheck.student.view.StudentDashboardFragment.SUBJECT_ID;

public class StudentAttendanceDetailActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        Intent intent = getIntent();
        int subjectId = intent.getIntExtra(SUBJECT_ID, 1);

        StudentAttendanceDetailFragment currentFragment = new StudentAttendanceDetailFragment(subjectId);
        currentFragment.setPresenter(new StudentAttendanceDetailPresenter(currentFragment, new StudentAttendanceDetailInteractor()));
        setCurrentFragment(currentFragment, true);
    }
}