package com.squarecheck.student.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.databinding.StudentDashboardToolbarBinding;
import com.squarecheck.student.contract.StudentDashboardContract;
import com.squarecheck.student.interactor.StudentDashboardInteractor;
import com.squarecheck.student.presenter.StudentDashboardPresenter;

public class StudentDashboardActivity extends BaseFragmentHolderActivity{
    StudentDashboardFragment studentDashboardFragment;
    StudentDashboardToolbarBinding inflate;

    @Override
    protected void initializeView() {
        super.initializeView();

        inflate = StudentDashboardToolbarBinding
                .inflate(this.getLayoutInflater(), binding.titleLayout, true);
        inflate.tvProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentDashboardFragment.showLogoutConfirmation();
            }
        });
    }

    @Override
    protected void initializeFragment() {
        studentDashboardFragment = new StudentDashboardFragment();
        studentDashboardFragment.setPresenter(new StudentDashboardPresenter(studentDashboardFragment, new StudentDashboardInteractor()));
        setCurrentFragment(studentDashboardFragment, false);
    }
}