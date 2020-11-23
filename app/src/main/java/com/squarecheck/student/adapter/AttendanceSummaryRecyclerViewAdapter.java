package com.squarecheck.student.adapter;

import android.view.View;

import com.squarecheck.R;
import com.squarecheck.base.adapter.BaseRecyclerAdapter;
import com.squarecheck.student.model.AttendanceStatusItem;

import java.util.List;

public class AttendanceSummaryRecyclerViewAdapter extends BaseRecyclerAdapter<List<AttendanceStatusItem>, AttendanceStatusItem> {
    public AttendanceSummaryRecyclerViewAdapter(List<AttendanceStatusItem> data) {
        super(data);
    }

    @Override
    protected View.OnClickListener setOnClickListener(int position) {
        return null;
    }

    @Override
    protected AttendanceStatusItem getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.attendance_summary_card;
    }
}
