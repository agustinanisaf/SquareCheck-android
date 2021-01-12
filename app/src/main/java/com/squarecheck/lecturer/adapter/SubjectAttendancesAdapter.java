package com.squarecheck.lecturer.adapter;

import android.view.View;

import com.squarecheck.R;
import com.squarecheck.base.adapter.BaseRecyclerAdapter;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.util.DateUtil;

import java.util.List;

public class SubjectAttendancesAdapter extends BaseRecyclerAdapter<List<ScheduleModel>, String> {

    private final ScheduleClickListener listener;

    public SubjectAttendancesAdapter(List<ScheduleModel> schedules, ScheduleClickListener listener) {
        super(schedules);
        this.listener = listener;
    }

    @Override
    protected View.OnClickListener setOnClickListener(int position) {
        return view -> listener.onScheduleClick(data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.schedule_layout;
    }

    @Override
    protected String getDataAtPosition(int position) {
        return DateUtil.getFullDate(data.get(position).getTime());
    }
}
