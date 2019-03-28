package com.example.deanery.activities.schedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.schedule.ScheduleItem;

import java.util.List;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ScheduleItemPlaceholder> {

    private List<ScheduleItem> scheduleItems;
    private String token;
    private Context context;

    public ScheduleRecyclerViewAdapter(List<ScheduleItem> scheduleItems, String token, Context context) {
        this.scheduleItems = scheduleItems;
        this.token = token;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleItemPlaceholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_lecturer, viewGroup, false); // andlys
        return new ScheduleItemPlaceholder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleItemPlaceholder scheduleItemPlaceholder, int i) {
        final ScheduleItem item = getScheduleItems().get(i);
        scheduleItemPlaceholder.copyProperties(item);
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

    class ScheduleItemPlaceholder extends RecyclerView.ViewHolder {
        private TextView timeInterval;
        private TextView discipline;
        private TextView group;
        private TextView lecturer;

        public ScheduleItemPlaceholder(@NonNull View itemView) {
            super(itemView);
            // andlys
        }

        void copyProperties(final ScheduleItem item) {
            timeInterval.setText(item.getTimeInterval());
            discipline.setText(item.getDiscipline());
            group.setText(item.getGroup());
            lecturer.setText(item.getLecturer());
        }

    }
}
