package com.example.deanery.activities.schedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.R;
import com.example.deanery.dataModels.schedule.Day;

import java.util.List;

public class ScheduleDayRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleDayRecyclerViewAdapter.DayPlaceholder> {

    private List<Day> days;
    private FragmentActivity fragmentActivity;

    public ScheduleDayRecyclerViewAdapter(List<Day> days, FragmentActivity activity) {
        this.days = days;
        this.fragmentActivity = activity;
    }

    public Context getContext() {
        return fragmentActivity;
    }

    public FragmentActivity getActivity() {
        return fragmentActivity;
    }

    @NonNull
    @Override
    public DayPlaceholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_schedule_day, viewGroup, false);
        return new DayPlaceholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayPlaceholder dayPlaceholder, int i) {
        final Day item = getDays().get(i);
        dayPlaceholder.copyProperties(item);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public List<Day> getDays() {
        return days;
    }

    class DayPlaceholder extends RecyclerView.ViewHolder {
        private TextView dayView;
        private Day day;
        private Button button;
        private View view;

        public DayPlaceholder(@NonNull View view) {
            super(view);
            this.view = view;
            dayView = (TextView) view.findViewById(R.id.day);
        }

        void copyProperties(final Day item) {
            day = item;
            dayView.setText(item.getDayName());
            initTimeSlots();
        }

        private void initTimeSlots() {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
            final int mColumnCount = 1;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ScheduleTimeRecyclerViewAdapter(day.getTimeSlots(), getActivity()));

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
