package com.squarecheck.lecturer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerAttendanceSummaryDetailBinding;
import com.squarecheck.databinding.LecturerAttendanceSummaryDetailToolbarBinding;
import com.squarecheck.lecturer.adapter.StudentAttendancesAdapter;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryDetailContract;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.model.PresenceModel;

import java.util.List;

public class LecturerAttendanceSummaryDetailFragment extends BaseFragment<LecturerAttendanceSummaryDetailActivity, LecturerAttendanceSummaryDetailContract.Presenter>
        implements LecturerAttendanceSummaryDetailContract.View{

    private final int scheduleId;
    private final Title title;
    private ContentLecturerAttendanceSummaryDetailBinding binding;

    public LecturerAttendanceSummaryDetailFragment(int scheduleId, Title title) {
        super();
        this.scheduleId = scheduleId;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentLecturerAttendanceSummaryDetailBinding.inflate(inflater, container, true);
        setTitleLayout(R.layout.lecturer_attendance_summary_detail_toolbar);
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
    public void showStudentAttendances(List<PresenceModel> data) {
        binding.recycler.setAdapter(new StudentAttendancesAdapter(data));
    }

    @Override
    public void showTitle(Title title) {
        ((LecturerAttendanceSummaryDetailToolbarBinding) getTitleLayout()).setTitle(title);
    }

    @Override
    public void setPresenter(LecturerAttendanceSummaryDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        showTitle(title);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.requestStudentAttendances(scheduleId);
    }
}
