package com.squarecheck.about;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squarecheck.databinding.DeveloperProfileBinding;

public class RecyclerVAdapterDeveloper extends RecyclerView.Adapter<RecyclerVAdapterDeveloper.MyViewHolder> {
    private final String[] developersNames;
    private final Drawable[] developerImages;

    public RecyclerVAdapterDeveloper(String[] developersNames, Drawable[] developerImages) {
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
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(developersNames[position], developerImages[position]);
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

        public void bind(String name, Drawable image) {
            binding.setDeveloperName(name);
            binding.setDeveloperPicture(image);
            binding.executePendingBindings();
        }
    }
}
