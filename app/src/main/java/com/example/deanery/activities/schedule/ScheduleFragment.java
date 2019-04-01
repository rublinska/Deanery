package com.example.deanery.activities.schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.RefreshInterface;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.activities.MainActivity;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.schedule.Day;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.schedule.TimeSlot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment implements RefreshInterface {

    private SwipeRefreshLayout swipeRefresh;
    private final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);
    private final List<Day> days = getTemporaryTestData();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScheduleFragment() { }

    @Deprecated // TODO andlys change this to real data
    private static List<Day> getTemporaryTestData() {
        final List<TimeSlot> scheduleItems = new ArrayList<>();
        scheduleItems.add(new TimeSlot("timeinterval", "lecturer", "group", "discipline", "auditory", "week"));
        scheduleItems.add(new TimeSlot("timeinterval2", "lecturer2", "group2", "discipline2", "auditory", "week"));
        scheduleItems.add(new TimeSlot("timeinterval3", "lecturer3", "group3", "discipline3", "auditory", "week"));
        final Day monday = new Day("Monday", scheduleItems);
        final List<TimeSlot> scheduleItems2 = new ArrayList<>();
        scheduleItems2.add(new TimeSlot("timeinterval4", "lecturer4", "group4", "discipline4", "auditory", "week"));
        scheduleItems2.add(new TimeSlot("timeinterval5", "lecturer5", "group5", "discipline5", "auditory", "week"));
        scheduleItems2.add(new TimeSlot("timeinterval6", "lecturer6", "group6", "discipline6", "auditory", "week"));
        final Day tuesday = new Day("Tuesday", scheduleItems2);
        final Day wednesday = new Day("Wednesday", scheduleItems);
        return Arrays.asList(monday, tuesday, wednesday);
    }

    public static ScheduleFragment newInstance(SwipeRefreshLayout swipe) {
        final ScheduleFragment result = new ScheduleFragment();
        result.swipeRefresh = swipe;
        result.storeSchedule();
        return result;
    }

    // andlys
    // TODO
    public void storeSchedule() {
        final Call<DeaneryGetList<ScheduleItem>> call = client.getAllScheduleItems(MainActivity.getToken());
        call.enqueue(new Callback<DeaneryGetList<ScheduleItem>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<ScheduleItem>> call, Response<DeaneryGetList<ScheduleItem>> response) {
                final DeaneryGetList<ScheduleItem> result = response.body();

            }

            @Override
            public void onFailure(Call<DeaneryGetList<ScheduleItem>> call, Throwable t) {
                Log.e("schedule","Failed to get all schedule items", t);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_schedule_list_days, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ScheduleDayRecyclerViewAdapter(days, getActivity()));
        return view;
    }

    @Override
    public void refreshItems() {
        // andlys
        Toast.makeText(getContext(), "Refreshed",
                Toast.LENGTH_LONG).show();
        swipeRefresh.setRefreshing(false);
    }
}
