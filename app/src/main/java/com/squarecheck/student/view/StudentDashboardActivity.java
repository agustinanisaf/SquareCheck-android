package com.squarecheck.student.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.databinding.StudentDashboardToolbarBinding;
import com.squarecheck.student.contract.StudentDashboardContract;

public class StudentDashboardActivity extends BaseFragmentHolderActivity{
    StudentDashboardFragment studentDashboardFragment;
    StudentDashboardToolbarBinding inflate;

    @Override
    protected void initializeView() {
        super.initializeView();

        inflate = StudentDashboardToolbarBinding
                .inflate(this.getLayoutInflater(), binding.titleLayout, true);
    }

    @Override
    protected void initializeFragment() {
        initializeView();

        studentDashboardFragment = new StudentDashboardFragment();
        setCurrentFragment(studentDashboardFragment, false);
    }
}