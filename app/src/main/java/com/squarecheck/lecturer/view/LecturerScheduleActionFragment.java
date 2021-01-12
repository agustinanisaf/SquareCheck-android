package com.squarecheck.lecturer.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerAttendanceBinding;
import com.squarecheck.databinding.LecturerAttendanceSummaryToolbarBinding;
import com.squarecheck.lecturer.contract.LecturerScheduleActionContract;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.model.Title;
import com.squarecheck.shared.util.DateUtil;

import static com.squarecheck.lecturer.view.LecturerDashboardFragment.SUBJECT_ID;

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
        disableOpenButton();
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
    public void changeButtonToClose() {
        Button openButton = (Button)getActivity().findViewById(R.id.btn_open_attendance);

        //change text and onclick to close attendance
        openButton.setText(R.string.close_attendance);
        openButton.setEnabled(true);
        openButton.setOnClickListener(view -> presenter.closeSchedule(scheduleId));
    }

    public void disableOpenButton(){
        Button openButton = (Button)getActivity().findViewById(R.id.btn_open_attendance);

        //change text and onclick to open attendance
        openButton.setText(R.string.open_attendance);
        openButton.setEnabled(false);
    }

    public void enableOpenButton(){
        Button openButton = (Button)getActivity().findViewById(R.id.btn_open_attendance);

        //change text and onclick to open attendance
        openButton.setText(R.string.open_attendance);
        openButton.setEnabled(true);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openSchedule(scheduleId);
            }
        });
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
    public void initView() {
        binding.btnOpenAttendance.setOnClickListener(v -> presenter.openSchedule(scheduleId));
        binding.btnRemoveAttendance.setOnClickListener(v -> presenter.removeSchedule(scheduleId));
    }

    private void redirectToSummary(int subjectId) {
        Intent intent = new Intent(getContext(), LecturerAttendanceSummaryActivity.class);
        intent.putExtra(SUBJECT_ID, subjectId);
        intent.putExtra(TITLE_ID, title);
        startActivity(intent);
    }

    public int getScheduleId() {
        return scheduleId;
    }
}
