package com.squarecheck.lecturer.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.squarecheck.R;
import com.squarecheck.base.view.BaseFragment;
import com.squarecheck.databinding.ContentLecturerAttendanceSummaryDetailBinding;
import com.squarecheck.databinding.LecturerAttendanceSummaryToolbarBinding;
import com.squarecheck.lecturer.adapter.StudentAttendancesAdapter;
import com.squarecheck.lecturer.contract.LecturerAttendanceSummaryDetailContract;
import com.squarecheck.shared.model.AttendanceStatusItem;
import com.squarecheck.shared.model.PresenceModel;
import com.squarecheck.shared.model.Title;
import com.squarecheck.student.adapter.AttendanceSummaryRecyclerViewAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class LecturerAttendanceSummaryDetailFragment extends BaseFragment<LecturerAttendanceSummaryDetailActivity, LecturerAttendanceSummaryDetailContract.Presenter>
        implements LecturerAttendanceSummaryDetailContract.View{

    private final int scheduleId;
    private final Title title;
    private final String time;
    private ContentLecturerAttendanceSummaryDetailBinding binding;

    public LecturerAttendanceSummaryDetailFragment(int scheduleId, Title title, String time) {
        super();
        this.scheduleId = scheduleId;
        this.title = title;
        this.time = time;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ContentLecturerAttendanceSummaryDetailBinding.inflate(inflater, container, true);
        setTitleLayout(R.layout.lecturer_attendance_summary_toolbar);
        return fragmentView;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void endLoading() {

    }

    @Override
    public void showStudentAttendances(List<PresenceModel> data) {
        binding.recycler.setAdapter(new StudentAttendancesAdapter(data));
    }

    @Override
    public void showTitle(Title title) {
        ((LecturerAttendanceSummaryToolbarBinding) getTitleLayout()).setTitle(title);
    }

    @Override
    public void showAttendanceStats(List<AttendanceStatusItem> attendanceStats) {
        binding.attendanceSummaryRecycler.setAdapter(new AttendanceSummaryRecyclerViewAdapter(attendanceStats));
        showChart(attendanceStats);
    }

    @Override
    public void initView() {
        showTitle(title);
        binding.setTime(time);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.requestStudentAttendances(scheduleId);
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
