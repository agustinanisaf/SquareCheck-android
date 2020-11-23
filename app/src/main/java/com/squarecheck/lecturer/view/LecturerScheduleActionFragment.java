package com.squarecheck.lecturer.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerAttendanceBinding;
import com.squarecheck.databinding.LecturerAttendanceSummaryToolbarBinding;
import com.squarecheck.lecturer.contract.LecturerScheduleActionContract;
import com.squarecheck.shared.model.Title;
import com.squarecheck.shared.util.DateUtil;
import com.squarecheck.student.model.ScheduleModel;

import static com.squarecheck.lecturer.view.LecturerDashboardFragment.SCHEDULE_ID;

public class LecturerScheduleActionFragment
        extends BaseFragment<LecturerScheduleActionActivity, LecturerScheduleActionContract.Presenter>
        implements LecturerScheduleActionContract.View {

    public static final String TITLE_ID = "TITLE_ID";
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
        setTitleLayout(R.layout.lecturer_attendance_summary_toolbar);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
        presenter.requestSchedule(scheduleId);
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
        binding.btnSummaryAttendance.setOnClickListener(v -> redirectToSummary(schedule.getSubject().getId()));
    }

    @Override
    public void showAttendances(String presence, String total) {
        binding.setPresence(presence);
        binding.setTotal(total);
    }

    @Override
    public void showTitle(Title title) {
        ((LecturerAttendanceSummaryToolbarBinding) getTitleLayout()).setTitle(title);
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPresenter(LecturerScheduleActionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.btnOpenAttendance.setOnClickListener(v -> presenter.openSchedule(scheduleId));
        binding.btnRemoveAttendance.setOnClickListener(v -> presenter.removeSchedule(scheduleId));
    }

    private void redirectToSummary(int subjectId) {
        Intent intent = new Intent(getContext(), LecturerAttendanceSummaryActivity.class);
        intent.putExtra(SCHEDULE_ID, subjectId);
        intent.putExtra(TITLE_ID, title);
        startActivity(intent);
    }
}
