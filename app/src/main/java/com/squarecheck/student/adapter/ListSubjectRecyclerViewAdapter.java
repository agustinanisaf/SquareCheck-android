package com.squarecheck.student.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.databinding.SubjectLayoutBinding;
import com.squarecheck.student.model.SubjectModel;

import java.util.List;


public class ListSubjectRecyclerViewAdapter extends RecyclerView.Adapter<ListSubjectRecyclerViewAdapter.SubjectViewHolder> {
    private List<SubjectModel> mSubjectList;
    private LayoutInflater mInflater;
    private SubjectClickListener listener;

    public ListSubjectRecyclerViewAdapter(Context context, List<SubjectModel> subjectList){
        mInflater = LayoutInflater.from(context);
        this.mSubjectList = subjectList;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(SubjectLayoutBinding.inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectModel mCurrent = mSubjectList.get(position);
        holder.binding.setSubject(mCurrent);
        holder.binding.getRoot().setOnClickListener(v -> listener.onSubjectClick(mCurrent));
    }

    @Override
    public int getItemCount() {
        return (mSubjectList != null) ? mSubjectList.size() : 0;
    }

    public void setListener(SubjectClickListener listener) {
        this.listener = listener;
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        SubjectLayoutBinding binding;

        public SubjectViewHolder(SubjectLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
