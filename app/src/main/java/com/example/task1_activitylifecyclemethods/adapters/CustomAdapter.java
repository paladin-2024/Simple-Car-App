package com.example.task1_activitylifecyclemethods.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.task1_activitylifecyclemethods.R;
import com.example.task1_activitylifecyclemethods.models.Data;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DataViewHolder> {

    private List<Data> listData = new ArrayList<>();

    public void setListData(List<Data> newData) {
        listData = new ArrayList<>(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data current = listData.get(position);
        holder.imageView.setImageResource(current.getImageResId());
        holder.topText.setText(current.getTopText());
        holder.bottomText.setText(current.getBottomText());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView topText;
        TextView bottomText;

        DataViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            topText = itemView.findViewById(R.id.titleTextView);
            bottomText = itemView.findViewById(R.id.subtitleTextView);
        }
    }
}