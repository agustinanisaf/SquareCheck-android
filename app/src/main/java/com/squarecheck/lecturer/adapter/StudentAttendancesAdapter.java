package com.squarecheck.lecturer.adapter;

import android.view.View;

import com.squarecheck.R;
import com.squarecheck.base.adapter.BaseRecyclerAdapter;
import com.squarecheck.shared.util.DateUtil;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

import java.util.List;

public class StudentAttendancesAdapter extends BaseRecyclerAdapter<List<PresenceModel>, String> {

    public StudentAttendancesAdapter(List<PresenceModel> presences) {
        super(presences);
    }

    @Override
    protected View.OnClickListener setOnClickListener(int position) {
        return null;
    }

    @Override
    protected int getLayoutIdForType(int viewType) {
        return R.layout.lecturer_attendance_layout;
    }

    @Override
    protected String getDataAtPosition(int position) {
        return DateUtil.getFullDate(data.get(position).getTime());
    }
}
