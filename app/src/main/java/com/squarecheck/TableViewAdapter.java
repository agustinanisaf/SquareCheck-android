package com.squarecheck;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter {
    private List presenceList;

    public TableViewAdapter(List presenceList) {
        this.presenceList = presenceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_layout, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {

            //rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_header_cell_bg);
            //rowViewHolder.txtTime.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtDate.setText("Date");
            rowViewHolder.txtTime.setText("Time");
        } else {
            PresenceModel modal = (PresenceModel) presenceList.get(rowPos - 1);

            //rowViewHolder.txtDate.setBackgroundResource(R.drawable.table_content_cell_bg);
            //rowViewHolder.txtTime.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtDate.setText(modal.getDate() + "");
            rowViewHolder.txtTime.setText(modal.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return presenceList.size() + 1;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate;
        TextView txtTime;

        RowViewHolder(View itemView) {
            super(itemView);
//            txtDate = itemView.findViewById(R.id.txtDate);
//            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
