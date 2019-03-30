package com.example.deanery.activities.schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.RefreshInterface;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.activities.MainActivity;
import com.example.deanery.dataModels.schedule.Day;
import com.example.deanery.dataModels.schedule.TimeSlot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleFragment extends Fragment implements RefreshInterface {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);

    static String token = "";
    final List<Day> days = getTemporaryTestData();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScheduleFragment() {
        // andlys
   }

    @Deprecated // TODO andlys change this to real data
    private static List<Day> getTemporaryTestData() {
        final List<TimeSlot> scheduleItems = new ArrayList<>();
        scheduleItems.add(new TimeSlot("timeinterval", "lecturer", "group", "discipline"));
        scheduleItems.add(new TimeSlot("timeinterval2", "lecturer2", "group2", "discipline2"));
        scheduleItems.add(new TimeSlot("timeinterval3", "lecturer3", "group3", "discipline3"));
        final Day monday = new Day("Monday", scheduleItems);
        final List<TimeSlot> scheduleItems2 = new ArrayList<>();
        scheduleItems2.add(new TimeSlot("timeinterval4", "lecturer4", "group4", "discipline4"));
        scheduleItems2.add(new TimeSlot("timeinterval5", "lecturer5", "group5", "discipline5"));
        scheduleItems2.add(new TimeSlot("timeinterval6", "lecturer6", "group6", "discipline6"));
        final Day tuesday = new Day("Tuesday", scheduleItems2);
        final Day wednesday = new Day("Wednesday", scheduleItems);
        return Arrays.asList(monday, tuesday, wednesday);
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ScheduleFragment newInstance(SwipeRefreshLayout columnCount) {
        ScheduleFragment fragment = new ScheduleFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity activity = new MainActivity();
        token = activity.getToken();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_schedule_list, container, false);
//      token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1NTI1MDQwODcsImV4cCI6MTU1MjUwNzY4NywibmJmIjoxNTUyNTA0MDg3LCJqdGkiOiJDT2RpaXdJZzRPV3lBT3NCIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.O-yZKKxEC4Mt5QPIqxHLTJVT_Sx1Zwzy0w2AIixE8to"
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new ScheduleDayRecyclerViewAdapter(days, getActivity()));
        }

//        Button btn = (Button) view.findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //    Log.i("lizatestOnclick", departmentSpinner.getFullName());
//                Toast.makeText(view.getContext(), "Check your credentials",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
        return view;
    }

    @Override
    public void refreshItems() {
        // andlys
    }
}
