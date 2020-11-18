package com.squarecheck;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class LecturerAttendanceSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_attendance_summary);
        setupRecyclerStats();
    }

    private void setupRecyclerStats() {
        RecyclerView rvAttendanceStats = findViewById(R.id.rv_attendance_stats);
        final List<Entry> entries = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            entries.add(new Entry(i, (float) Math.random()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Hadir");
        dataSet.setColor(getResources().getColor(R.color.primary));
        dataSet.setValueTextSize(0);
        dataSet.setDrawHighlightIndicators(false);
        dataSet.setLineWidth(10);
        dataSet.setCircleColor(Color.TRANSPARENT);
        final LineData lineData = new LineData(dataSet);

        rvAttendanceStats.setAdapter(new RecyclerView.Adapter<AttendanceStatsHolder>() {
            @NonNull
            @Override
            public AttendanceStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_stat_layout, parent, false);
                return new AttendanceStatsHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull AttendanceStatsHolder holder, int position) {
                holder.lineChart.setData(lineData);
                holder.lineChart.invalidate();
                holder.lineChart.setGridBackgroundColor(getResources().getColor(R.color.hadir));
                holder.lineChart.getDescription().setEnabled(false);
                holder.lineChart.setDrawGridBackground(true);
                holder.lineChart.getXAxis().setDrawGridLines(false);
                holder.lineChart.getXAxis().setDrawAxisLine(false);
                holder.lineChart.getXAxis().setDrawLabels(false);
                holder.lineChart.getAxisLeft().setDrawGridLines(false);
                holder.lineChart.getAxisLeft().setDrawAxisLine(false);
                holder.lineChart.getAxisLeft().setDrawLabels(false);
                holder.lineChart.getAxisRight().setDrawGridLines(false);
                holder.lineChart.getAxisRight().setDrawAxisLine(false);
                holder.lineChart.getAxisRight().setDrawLabels(false);
                holder.lineChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                holder.lineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                holder.lineChart.getLegend().setOrientation(Legend.LegendOrientation.HORIZONTAL);
                holder.lineChart.getLegend().setDrawInside(false);
                holder.lineChart.getLegend().setTextColor(getResources().getColor(R.color.primary));
                holder.lineChart.getLegend().setTextSize(24);
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });
    }

    static class AttendanceStatsHolder extends RecyclerView.ViewHolder {
        final LineChart lineChart;

        public AttendanceStatsHolder(@NonNull View itemView) {
            super(itemView);
            lineChart = itemView.findViewById(R.id.attendance_stat_chart);
        }
    }
}
