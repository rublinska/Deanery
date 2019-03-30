package com.example.deanery.activities.schedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.schedule.TimeSlot;

import java.util.List;

public class ScheduleTimeRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleTimeRecyclerViewAdapter.TimeSlotPlaceholder> {

    private List<TimeSlot> scheduleItems;
    private FragmentActivity fragmentActivity;

    public ScheduleTimeRecyclerViewAdapter(List<TimeSlot> scheduleItems, FragmentActivity activity) {
        this.scheduleItems = scheduleItems;
        this.fragmentActivity = activity;
    }

    public Context getContext() {
        return fragmentActivity;
    }

    @NonNull
    @Override
    public TimeSlotPlaceholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_schedule_time_slot, viewGroup, false);
        return new TimeSlotPlaceholder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSlotPlaceholder timeSlotPlaceholder, int i) {
        final TimeSlot item = getScheduleItems().get(i);
        timeSlotPlaceholder.copyProperties(item);
    }

    @Override
    public int getItemCount() {
        return scheduleItems.size();
    }

    public List<TimeSlot> getScheduleItems() {
        return scheduleItems;
    }

    class TimeSlotPlaceholder extends RecyclerView.ViewHolder {
        private TextView timeInterval;
        private TextView discipline;
        private TextView group;
        private TextView lecturer;
        private TimeSlot timeSlot;
        private Button button;

        public TimeSlotPlaceholder(@NonNull View view) {
            super(view);
            timeInterval = (TextView) view.findViewById(R.id.schedule_time);
            discipline = (TextView) view.findViewById(R.id.schedule_discipline);
            lecturer = (TextView) view.findViewById(R.id.schedule_lecturer);
            group = (TextView) view.findViewById(R.id.schedule_group);
        }

        void copyProperties(final TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
            timeInterval.setText(this.timeSlot.getTimeInterval());
            discipline.setText(this.timeSlot.getDiscipline());
            group.setText(this.timeSlot.getGroup());
            lecturer.setText(this.timeSlot.getLecturer());
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
