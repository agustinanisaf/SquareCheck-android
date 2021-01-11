package com.squarecheck.student.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentStudentAttendanceDetailBinding;
import com.squarecheck.databinding.StudentAttendanceDetailToolbarBinding;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.adapter.AttendanceRecyclerViewAdapter;
import com.squarecheck.student.adapter.AttendanceSummaryRecyclerViewAdapter;
import com.squarecheck.student.contract.StudentAttendanceDetailContract;
import com.squarecheck.student.model.AttendanceItem;
import com.squarecheck.student.model.AttendanceStatusItem;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class StudentAttendanceDetailFragment
        extends BaseFragment<StudentAttendanceDetailActivity, StudentAttendanceDetailContract.Presenter>
        implements StudentAttendanceDetailContract.View {

    private final int subjectId;
    private final Title title;
    private ContentStudentAttendanceDetailBinding binding;

    public StudentAttendanceDetailFragment(int subjectId, Title title) {
        super();
        this.subjectId = subjectId;
        this.title = title;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
        presenter.requestAttendances(subjectId);
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
        showTitle(title);
        binding.attendanceRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.attendanceSummaryRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void startLoading() {
        binding.attendanceSummaryRecycler.setVisibility(View.GONE);
        binding.attendanceRecycler.setVisibility(View.GONE);
        binding.attendanceChart.setVisibility(View.GONE);
        binding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.attendanceSummaryRecycler.setVisibility(View.VISIBLE);
        binding.attendanceRecycler.setVisibility(View.VISIBLE);
        binding.attendanceChart.setVisibility(View.VISIBLE);
        binding.progressCircular.setVisibility(View.GONE);
    }

    @Override
    public void showAttendances(List<AttendanceItem> attendances) {
        binding.attendanceRecycler.setAdapter(new AttendanceRecyclerViewAdapter(attendances));
    }

    @Override
    public void showAttendanceStats(List<AttendanceStatusItem> attendanceStats) {
        binding.attendanceSummaryRecycler.setAdapter(new AttendanceSummaryRecyclerViewAdapter(attendanceStats));
        showChart(attendanceStats);
    }

    @Override
    public void showTitle(Title title) {
        ((StudentAttendanceDetailToolbarBinding) getTitleLayout()).setData(title);
    }

    private void showChart(List<AttendanceStatusItem> attendanceStats) {
        PieChart pieChart = binding.attendanceChart;
        Log.d("TAG", "onCreate: " + pieChart.getHoleRadius());
        List<PieEntry> entries = attendanceStats.stream().map(f -> new PieEntry(Float.parseFloat(f.getTotal()), f.getStatus())).collect(Collectors.toList());

        PieDataSet set = new PieDataSet(entries, "Attendance");
        set.setColors(new int[]{R.color.hadir, R.color.ijin, R.color.telat, R.color.alpa}, getContext());
        set.setDrawValues(false);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

        AttendanceStatusItem item = attendanceStats.stream().max((Comparator<AttendanceStatusItem>) (t0, t1) -> {
            int i0 = Integer.parseInt(t0.getTotal());
            int i1 = Integer.parseInt(t1.getTotal());
            if (i0 > i1) return 1;
            else if (i0 == i1) return 0;
            return -1;
        }).orElseThrow(NoSuchElementException::new);
        pieChart.setDescription(null);
        pieChart.setCenterText(Math.round(Float.parseFloat(item.getTotal()) / attendanceStats.stream().mapToInt(v -> Integer.parseInt(v.getTotal())).sum() * 100) + "%");
        pieChart.setCenterTextColor(getResources().getColor(item.getColor()));
        pieChart.setCenterTextSize(28.f);
        pieChart.setTransparentCircleRadius(0.0f);
        pieChart.setHoleRadius(75.f);
        pieChart.setEntryLabelTextSize(0.0f);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawMarkers(false);
        pieChart.getLegend().setEnabled(false);
    }

}
