package com.squarecheck.lecturer.view;

import android.view.View;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.databinding.StudentDashboardToolbarBinding;
import com.squarecheck.lecturer.interactor.LecturerDashboardInteractor;
import com.squarecheck.lecturer.presenter.LecturerDashboardPresenter;

public class LecturerDashboardActivity extends BaseFragmentHolderActivity {
    StudentDashboardToolbarBinding toolbarBinding;

    @Override
    protected void initializeView() {
        super.initializeView();

        toolbarBinding = StudentDashboardToolbarBinding
                .inflate(this.getLayoutInflater(), binding.titleLayout, true);
    }

    @Override
    protected void initializeFragment() {
        LecturerDashboardFragment currentFragment = new LecturerDashboardFragment();
        currentFragment.setPresenter(new LecturerDashboardPresenter(currentFragment, new LecturerDashboardInteractor()));
        setCurrentFragment(currentFragment, false);
        toolbarBinding.tvProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFragment.showLogoutConfirmation();
            }
        });
    }
}
