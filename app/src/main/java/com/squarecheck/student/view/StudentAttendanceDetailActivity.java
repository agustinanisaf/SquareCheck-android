package com.squarecheck.student.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.squarecheck.R;
import com.squarecheck.student.adapter.TableViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_attendance_detail);
        // TODO: Fix Recycler View
        RecyclerView recyclerView = findViewById(R.id.attendance_recycler);
        TableViewAdapter adapter = new TableViewAdapter(getPresenceList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        PieChart pieChart = findViewById(R.id.attendance_chart);
        Log.d("TAG", "onCreate: " + pieChart.getHoleRadius());
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(102.0f, getString(R.string.hadir_label)));
        entries.add(new PieEntry(12.0f, getString(R.string.izin_label)));
        entries.add(new PieEntry(23.0f, getString(R.string.terlambat_label)));
        entries.add(new PieEntry(2.0f, getString(R.string.alpa_label)));

        PieDataSet set = new PieDataSet(entries, "Attendance");
        set.setColors(new int[]{R.color.hadir, R.color.ijin, R.color.telat, R.color.alpa}, this);
        set.setDrawValues(false);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.setDescription(null);
        pieChart.setCenterText("80%");
        pieChart.setCenterTextColor(getResources().getColor(R.color.hadir));
        pieChart.setCenterTextSize(28.f);
        pieChart.setTransparentCircleRadius(0.0f);
        pieChart.setHoleRadius(75.f);
        pieChart.setEntryLabelTextSize(0.0f);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawMarkers(false);
        pieChart.getLegend().setEnabled(false);
    }

    private List getPresenceList() {
        List presenceList = new ArrayList<>();

        return presenceList;
    }
}