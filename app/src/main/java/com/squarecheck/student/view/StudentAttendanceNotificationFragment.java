package com.squarecheck.student.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentNotificationBinding;
import com.squarecheck.student.contract.StudentAttendanceNotificationContract;
import com.squarecheck.student.model.NotificationPresenceItem;

public class StudentAttendanceNotificationFragment extends
        BaseFragment<StudentAttendanceNotificationActivity, StudentAttendanceNotificationContract.Presenter> implements
        StudentAttendanceNotificationContract.View {

    private final NotificationPresenceItem presence;
    private ContentNotificationBinding binding;

    public StudentAttendanceNotificationFragment(NotificationPresenceItem presence) {
        super();
        this.presence = presence;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentNotificationBinding.inflate(inflater, container, true);
        return fragmentView;
    }

    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void backToDashboard() {
        activity.finish();
    }

    @Override
    public void setPresenter(StudentAttendanceNotificationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.btnNotificationFinish.setOnClickListener(v -> backToDashboard());
        binding.setPresence(presence);
    }
}
