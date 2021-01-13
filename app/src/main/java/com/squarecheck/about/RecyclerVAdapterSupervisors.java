package com.squarecheck.about;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.databinding.DeveloperProfileBinding;
import com.squarecheck.databinding.SupervisorsMentorsItemBinding;

public class RecyclerVAdapterSupervisors extends RecyclerView.Adapter<RecyclerVAdapterSupervisors.MyViewHolder>{
    private final String[] nameList;

    public RecyclerVAdapterSupervisors(String[] nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public RecyclerVAdapterSupervisors.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        SupervisorsMentorsItemBinding binding =
                SupervisorsMentorsItemBinding.inflate(layoutInflater, parent, false);
        return new RecyclerVAdapterSupervisors.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVAdapterSupervisors.MyViewHolder holder, int position) {
        holder.bind(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return nameList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final SupervisorsMentorsItemBinding binding;

        public MyViewHolder(SupervisorsMentorsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String name) {
            binding.setSupervisorMentorName(name);
            binding.executePendingBindings();
        }
    }
}
