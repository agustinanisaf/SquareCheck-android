package com.squarecheck.student.view;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.squarecheck.base.view.BaseFragmentHolderActivity;
import com.squarecheck.shared.Constants;
import com.squarecheck.student.model.NotificationPresenceItem;
import com.squarecheck.student.presenter.StudentAttendanceNotificationPresenter;

public class StudentAttendanceNotificationActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeView() {
        super.initializeView();
        binding.toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initializeFragment() {
        Intent intent = getIntent();
        NotificationPresenceItem presence = new Gson().fromJson(intent.getStringExtra(Constants.PRESENCE_ID), NotificationPresenceItem.class);

        StudentAttendanceNotificationFragment currentFragment = new StudentAttendanceNotificationFragment(presence);
        currentFragment.setPresenter(new StudentAttendanceNotificationPresenter(currentFragment));
        setCurrentFragment(currentFragment, true);
    }
}
