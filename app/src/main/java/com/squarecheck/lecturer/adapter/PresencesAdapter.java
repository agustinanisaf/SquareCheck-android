package com.squarecheck.lecturer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PresencesAdapter extends RecyclerView.Adapter<PresencesAdapter.ViewHolder>{
    private final List<PresenceModel> presences;

    public PresencesAdapter(List<PresenceModel> presences) {
        this.presences = presences;
    }

    @NonNull
    @Override
    public PresencesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PresencesAdapter.ViewHolder(LecturerClassSubjectBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PresenceModel presence = presences.get(position);
        holder.binding.setPresence(presence);
    }

    @Override
    public int getItemCount() {
        return (presences == null) ? 0 : presences.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LecturerClassSubjectBinding binding;

        public ViewHolder(@NotNull LecturerClassSubjectBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
