package com.squarecheck.about;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.squarecheck.databinding.DeveloperProfileBinding;

public class RecyclerVAdapterDeveloper extends RecyclerView.Adapter<RecyclerVAdapterDeveloper.MyViewHolder> {
    private final String[] developersNames;
    private final int[] developerImages;
    private RequestManager glide;

    public RecyclerVAdapterDeveloper(String[] developersNames, int[] developerImages) {
        this.developersNames = developersNames;
        this.developerImages = developerImages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        DeveloperProfileBinding binding =
                DeveloperProfileBinding.inflate(layoutInflater, parent, false);
        glide = Glide.with(parent.getContext());
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(developersNames[position]);
        glide.load(developerImages[position]).circleCrop().into(holder.binding.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return developersNames.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final DeveloperProfileBinding binding;

        public MyViewHolder(DeveloperProfileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String name) {
            binding.setDeveloperName(name);
            binding.executePendingBindings();
        }
    }
}
