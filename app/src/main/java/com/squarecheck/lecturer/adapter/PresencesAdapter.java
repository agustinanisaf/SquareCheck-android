package com.squarecheck.lecturer.adapter;

import android.view.View;

import com.squarecheck.R;
import com.squarecheck.base.adapter.BaseRecyclerAdapter;
import com.squarecheck.student.model.PresenceModel;

import java.util.List;

//public class PresencesAdapter extends RecyclerView.Adapter<PresencesAdapter.ViewHolder>{
//    private final List<PresenceModel> presences;
//
//    public PresencesAdapter(List<PresenceModel> presences) {
//        this.presences = presences;
//    }
//
//    @NonNull
//    @Override
//    public PresencesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        return new PresencesAdapter.ViewHolder(LecturerClassSubjectBinding.inflate(inflater, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final PresenceModel presence = presences.get(position);
//        holder.binding.setPresence(presence);
//    }
//
//    @Override
//    public int getItemCount() {
//        return (presences == null) ? 0 : presences.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        private final LecturerClassSubjectBinding binding;
//
//        public ViewHolder(@NotNull LecturerClassSubjectBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//    }
//}

public class PresencesAdapter extends BaseRecyclerAdapter<List<PresenceModel>, PresenceModel> {

    public PresencesAdapter(List<PresenceModel> data) {
        super(data);
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
    protected PresenceModel getDataAtPosition(int position) {
        return data.get(position);
    }
}
