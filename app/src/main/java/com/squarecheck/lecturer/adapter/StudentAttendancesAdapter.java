package com.squarecheck.lecturer.adapter;

import android.view.View;

import com.squarecheck.R;
import com.squarecheck.base.adapter.BaseRecyclerAdapter;
import com.squarecheck.shared.model.PresenceModel;
import com.squarecheck.shared.model.StudentAttendanceModel;

import java.util.List;

public class StudentAttendancesAdapter extends BaseRecyclerAdapter<List<PresenceModel>, StudentAttendanceModel> {

    public StudentAttendancesAdapter(List<PresenceModel> presences) {
        super(presences);
    }

    @Override
    protected View.OnClickListener setOnClickListener(int position) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.lecturer_attendance_layout;
    }

    @Override
    protected StudentAttendanceModel getDataAtPosition(int position) {
        return StudentAttendanceModel.fromPresenceModel(data.get(position));
    }
}
