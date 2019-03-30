package com.example.deanery.activities.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.activities.lecturer.LecturerRecyclerViewAdapter;
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
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleItemPlaceholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_schedule, viewGroup, false);

        return new ScheduleRecyclerViewAdapter.ScheduleItemPlaceholder(rowView);
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
        private ScheduleItem scheduleItem;
        private ScheduleItem item;
        private Button button;

        public ScheduleItemPlaceholder(@NonNull View view) {
            super(view);
            // andlys
            timeInterval = (TextView) view.findViewById(R.id.time0830);
        }

        void copyProperties(final ScheduleItem item) {
//            timeInterval.setText(item.getTimeInterval());
//            discipline.setText(item.getDiscipline());
//            group.setText(item.getGroup());
//            lecturer.setText(item.getLecturer());
//            this.scheduleItem = item;
        }

        void setOnClick() {
            // andlys
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //    Log.i("lizatestOnclick", departmentSpinner.getFullName());
//                    Intent i = new Intent(v.getContext(), ScheduleUpdateActivity.class);
//                    Bundle mBundle = new Bundle();
//                    mBundle.putParcelable("schedule", item);
//                    i.putExtras(mBundle);
//                    i.putExtra("token", token);
//                    v.getContext().startActivity(i);
//                }
//            });
        }

    }
}
