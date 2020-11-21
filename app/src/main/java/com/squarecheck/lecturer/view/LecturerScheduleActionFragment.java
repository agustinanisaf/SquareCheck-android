package com.squarecheck.lecturer.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerAttendanceBinding;
import com.squarecheck.lecturer.contract.LecturerScheduleActionContract;
import com.squarecheck.shared.util.DateUtil;
import com.squarecheck.student.model.ScheduleModel;

import static com.squarecheck.lecturer.view.LecturerDashboardFragment.SCHEDULE_ID;

public class LecturerScheduleActionFragment
        extends BaseFragment<LecturerScheduleActionActivity, LecturerScheduleActionContract.Presenter>
        implements LecturerScheduleActionContract.View {

    private int scheduleId;
    private ContentLecturerAttendanceBinding binding;

    public LecturerScheduleActionFragment() {
    }

    public LecturerScheduleActionFragment(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentLecturerAttendanceBinding.inflate(inflater, container, true);
        presenter.start();
        presenter.requestSchedule(scheduleId);
        return fragmentView;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSchedule(ScheduleModel schedule) {
        binding.setDay(DateUtil.getDay(schedule.getTime()));
        binding.setDate(DateUtil.getDate(schedule.getTime()));
    }

    @Override
    public void showAttendances(String presence, String total) {
        binding.setPresence(presence);
        binding.setTotal(total);
    }

    @Override
    public void setPresenter(LecturerScheduleActionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.btnOpenAttendance.setOnClickListener(v -> presenter.openSchedule(scheduleId));
        binding.btnRemoveAttendance.setOnClickListener(v -> presenter.removeSchedule(scheduleId));
        binding.btnSummaryAttendance.setOnClickListener(v -> redirectToSummary(scheduleId));
    }

    private void redirectToSummary(int scheduleId) {
        Intent intent = new Intent(getContext(), LecturerAttendanceSummaryActivity.class);
        intent.getIntExtra(SCHEDULE_ID, scheduleId);
        startActivity(intent);
    }
}
