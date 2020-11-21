package com.squarecheck.lecturer.view;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.lecturer.interactor.LecturerDashboardInteractor;
import com.squarecheck.lecturer.presenter.LecturerDashboardPresenter;

public class LecturerDashboardActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        LecturerDashboardFragment currentFragment = new LecturerDashboardFragment();
        currentFragment.setPresenter(new LecturerDashboardPresenter(currentFragment, new LecturerDashboardInteractor()));
        setCurrentFragment(currentFragment, false);
    }
}
