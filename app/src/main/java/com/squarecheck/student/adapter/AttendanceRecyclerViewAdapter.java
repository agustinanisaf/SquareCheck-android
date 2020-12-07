package com.squarecheck.student.adapter;

import android.view.View;

import com.squarecheck.R;
import com.squarecheck.base.adapter.BaseRecyclerAdapter;
import com.squarecheck.student.model.AttendanceItem;

import java.util.List;

public class AttendanceRecyclerViewAdapter extends BaseRecyclerAdapter<List<AttendanceItem>, AttendanceItem> {
    public AttendanceRecyclerViewAdapter(List<AttendanceItem> data) {
        super(data);
    }

    @Override
    protected View.OnClickListener setOnClickListener(int position) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.student_attendance_layout;
    }

    @Override
    protected AttendanceItem getDataAtPosition(int position) {
        AttendanceItem item = data.get(position);

        int background = (position % 2 == 0) ? R.color.white : R.color.gray;
        item.setBackground(background);

        return item;
    }
}
