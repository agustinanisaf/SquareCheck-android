package com.squarecheck.lecturer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.databinding.LecturerClassSubjectBinding;
import com.squarecheck.student.model.ScheduleModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.squarecheck.lecturer.adapter.SchedulesAdapter.ViewHolder;

public class SchedulesAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final ScheduleClickListener clickListener;
    private final List<ScheduleModel> schedules;

    public SchedulesAdapter(ScheduleClickListener clickListener, List<ScheduleModel> schedules) {
        this.clickListener = clickListener;
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(LecturerClassSubjectBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ScheduleModel schedule = schedules.get(position);
        holder.binding.setSchedule(schedule);
        holder.binding.tvClassSubject.setOnClickListener(v -> clickListener.onScheduleClick(schedule));
    }

    @Override
    public int getItemCount() {
        return (schedules == null) ? 0 : schedules.size();
    }

    public interface ScheduleClickListener {
        void onScheduleClick(ScheduleModel schedule);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LecturerClassSubjectBinding binding;

        public ViewHolder(@NotNull LecturerClassSubjectBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
