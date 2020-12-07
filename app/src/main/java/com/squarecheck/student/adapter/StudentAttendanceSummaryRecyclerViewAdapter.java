package com.squarecheck.student.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.BR;
import com.squarecheck.R;
import com.squarecheck.student.model.AttendanceStatusItem;

import java.util.List;

public class StudentAttendanceSummaryRecyclerViewAdapter extends
        RecyclerView.Adapter<StudentAttendanceSummaryRecyclerViewAdapter.ViewHolder> {

    private List<AttendanceStatusItem> data;

    public StudentAttendanceSummaryRecyclerViewAdapter(List<AttendanceStatusItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        int width = parent.getMeasuredWidth() / 4;

        binding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.MATCH_PARENT));

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.attendance_summary_card;
    }

    @Override
    public int getItemCount() {
        // Static
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(AttendanceStatusItem item) {
            binding.setVariable(BR.data, item);
        }
    }
}
