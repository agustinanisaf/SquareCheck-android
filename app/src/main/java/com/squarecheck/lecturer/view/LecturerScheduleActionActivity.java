package com.squarecheck.lecturer.view;

import android.content.Intent;

import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.lecturer.interactor.LecturerScheduleActionInteractor;
import com.squarecheck.lecturer.presenter.LecturerScheduleActionPresenter;
import com.squarecheck.shared.Constants;

public class LecturerScheduleActionActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeView() {
        super.initializeView();

    }

    @Override
    protected void initializeFragment() {
        Intent intent = getIntent();
        int scheduleId = intent.getIntExtra(Constants.SUBJECT_ID, 1);

        LecturerScheduleActionFragment currentFragment = new LecturerScheduleActionFragment(scheduleId);
        currentFragment.setPresenter(new LecturerScheduleActionPresenter(currentFragment, new LecturerScheduleActionInteractor()));
        setCurrentFragment(currentFragment, true);
    }
}
