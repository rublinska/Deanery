package com.example.deanery.activities.schedule;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
        timeSlotPlaceholder.setOnClick();
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
        private TextView auditory;
        private TextView week;
        private TimeSlot timeSlot;
        private View timeSlotRow;

        public TimeSlotPlaceholder(@NonNull View view) {
            super(view);
            timeInterval = (TextView) view.findViewById(R.id.schedule_time);
            discipline = (TextView) view.findViewById(R.id.schedule_discipline);
            lecturer = (TextView) view.findViewById(R.id.schedule_lecturer);
            group = (TextView) view.findViewById(R.id.schedule_group);
            auditory = (TextView) view.findViewById(R.id.schedule_auditory);
            week = (TextView) view.findViewById(R.id.schedule_week);
            timeSlotRow = view.findViewById(R.id.schedule_time_slot_row);
        }

        void copyProperties(final TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
            timeInterval.setText(this.timeSlot.getTimeInterval());
            discipline.setText(this.timeSlot.getDiscipline());
            group.setText(this.timeSlot.getGroup());
            lecturer.setText(this.timeSlot.getLecturer());
            auditory.setText(this.timeSlot.getAuditory());
            week.setText(this.timeSlot.getWeek());
        }

        void setOnClick() {
            timeSlotRow.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motion) {
                    if (motion.getAction() == MotionEvent.ACTION_UP) {
                        final Drawable background =
                                ResourcesCompat.getDrawable(getContext().getResources(),
                                        R.drawable.rounded_corners_for_cards_light_blue,
                                        null);
                        view.setBackground(background);
                        return true;
                    } else if (motion.getAction() == MotionEvent.ACTION_DOWN){
                        final Drawable background =
                                ResourcesCompat.getDrawable(getContext().getResources(),
                                        R.drawable.rounded_corners_for_cards,
                                        null);
                        view.setBackground(background);
                        Intent i = new Intent(view.getContext(), ScheduleUpdateActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putParcelable("schedule_time_slot", timeSlot);
                        mBundle.putString("specialty", fragmentActivity.getIntent().getExtras().getString("specialty"));
                        mBundle.putString("semester", fragmentActivity.getIntent().getExtras().getString("semester"));
                        i.putExtras(mBundle);
                        view.getContext().startActivity(i);
                        return true;
                    }
                    return false;
                }
            });
        }

    }
}
