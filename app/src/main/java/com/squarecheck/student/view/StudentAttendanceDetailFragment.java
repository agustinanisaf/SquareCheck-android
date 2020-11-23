package com.squarecheck.student.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentStudentAttendanceDetailBinding;
import com.squarecheck.student.contract.StudentAttendanceDetailContract;

public class StudentAttendanceDetailFragment
        extends BaseFragment<StudentAttendanceDetailActivity, StudentAttendanceDetailContract.Presenter>
        implements StudentAttendanceDetailContract.View {

    private final int subjectId;
    private ContentStudentAttendanceDetailBinding binding;

    public StudentAttendanceDetailFragment(int subjectId) {
        super();
        this.subjectId = subjectId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentStudentAttendanceDetailBinding.inflate(inflater, container, true);
        setTitleLayout(R.layout.student_attendance_detail_toolbar);
        return fragmentView;
    }

    @Override
    public void setPresenter(StudentAttendanceDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initView() {
        binding.attendanceRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.attendanceSummaryRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}
