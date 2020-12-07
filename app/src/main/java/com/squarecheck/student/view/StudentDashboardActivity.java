package com.squarecheck.student.view;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.databinding.StudentDashboardToolbarBinding;
import com.squarecheck.student.interactor.StudentDashboardInteractor;
import com.squarecheck.student.presenter.StudentDashboardPresenter;

public class StudentDashboardActivity extends BaseFragmentHolderActivity {
    StudentDashboardFragment studentDashboardFragment;
    StudentDashboardToolbarBinding inflate;

    @Override
    protected void initializeFragment() {
        studentDashboardFragment = new StudentDashboardFragment();
        studentDashboardFragment.setPresenter(new StudentDashboardPresenter(studentDashboardFragment, new StudentDashboardInteractor()));
        setCurrentFragment(studentDashboardFragment, false);
    }
}