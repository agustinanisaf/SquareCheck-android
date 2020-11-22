package com.squarecheck.lecturer.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerAttendanceSummaryBinding;
import com.squarecheck.databinding.LecturerAttendanceSummaryToolbarBinding;
import com.squarecheck.lecturer.adapter.SubjectAttendancesAdapter;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryContract;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public class LecturerAttendanceSummaryFragment
        extends BaseFragment<LecturerAttendanceSummaryActivity, LecturerAttendanceSummaryContract.Presenter>
        implements LecturerAttendanceSummaryContract.View {

    private static final String SCHEDULE_ID = "SCHEDULE_ID";
    private final int subjectId;
    private final Title title;
    private ContentLecturerAttendanceSummaryBinding binding;

    public LecturerAttendanceSummaryFragment(int subjectId, Title title) {
        super();
        this.subjectId = subjectId;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentLecturerAttendanceSummaryBinding.inflate(inflater, container, true);
        setTitleLayout(R.layout.lecturer_attendance_summary_toolbar);
        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showSubjectAttendances(List<ScheduleModel> data) {
        binding.recycler.setAdapter(new SubjectAttendancesAdapter(data, this::redirectToSummaryDetail));
    }

    @Override
    public void showTitle(Title title) {
        ((LecturerAttendanceSummaryToolbarBinding) getTitleLayout()).setTitle(title);
    }

    @Override
    public void setPresenter(LecturerAttendanceSummaryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        showTitle(title);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.requestSubjectAttendances(subjectId);
    }

    public void redirectToSummaryDetail(ScheduleModel schedule) {
        Intent intent = new Intent(getContext(), LecturerAttendanceSummaryDetailActivity.class);
        intent.putExtra(SCHEDULE_ID, schedule.getId());
        startActivity(intent);
    }
}