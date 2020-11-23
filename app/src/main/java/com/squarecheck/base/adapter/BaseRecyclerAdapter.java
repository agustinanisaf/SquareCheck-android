package com.squarecheck.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.squarecheck.BR;

import java.util.Collection;

public abstract class BaseRecyclerAdapter<T extends Collection, U> extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<U>> {

    protected T data;

    public BaseRecyclerAdapter(T data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder<U> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getDataAtPosition(position));
        holder.setOnClickListener(setOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    protected abstract View.OnClickListener setOnClickListener(int position);

    protected abstract int getLayoutIdForType(int viewType);

    protected abstract U getDataAtPosition(int position);

    public static class ViewHolder<U> extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public ViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(U data) {
            binding.setVariable(BR.data, data);
            binding.executePendingBindings();
        }

        public void setOnClickListener(View.OnClickListener listener) {
            this.binding.getRoot().setOnClickListener(listener);
        }
    }
}
