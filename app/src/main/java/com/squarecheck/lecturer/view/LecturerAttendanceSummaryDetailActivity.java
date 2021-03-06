package com.squarecheck.lecturer.view;

import android.content.Intent;

import com.google.gson.Gson;
import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.lecturer.interactor.LecturerAttendanceSummaryDetailInteractor;
import com.squarecheck.lecturer.presenter.LecturerAttendanceSummaryDetailPresenter;
import com.squarecheck.shared.Constants;
import com.squarecheck.shared.model.Title;

public class LecturerAttendanceSummaryDetailActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        Intent intent = getIntent();
        int scheduleId = intent.getIntExtra(Constants.SCHEDULE_ID, 1);
        Title title = new Gson().fromJson(intent.getStringExtra(Constants.TITLE_ID), Title.class);
        String time = intent.getStringExtra("TIME_ID");

        LecturerAttendanceSummaryDetailFragment currentFragment = new LecturerAttendanceSummaryDetailFragment(scheduleId, title, time);
        currentFragment.setPresenter(new LecturerAttendanceSummaryDetailPresenter(currentFragment, new LecturerAttendanceSummaryDetailInteractor()));
        setCurrentFragment(currentFragment, true);
    }
}
