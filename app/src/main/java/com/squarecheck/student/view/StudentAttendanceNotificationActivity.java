package com.squarecheck.student.view;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.student.presenter.StudentAttendanceNotificationPresenter;

public class StudentAttendanceNotificationActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        StudentAttendanceNotificationFragment currentFragment = new StudentAttendanceNotificationFragment();
        currentFragment.setPresenter(new StudentAttendanceNotificationPresenter(currentFragment));
        setCurrentFragment(currentFragment, false);
    }
}
