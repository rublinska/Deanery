package com.example.deanery.activities.schedule;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.RefreshInterface;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.activities.MainActivity;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.discipline.Discipline;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.example.deanery.dataModels.schedule.AcademicWeek;
import com.example.deanery.dataModels.schedule.Day;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.schedule.TimeSlot;
import com.example.deanery.dataModels.specialty.Specialty;
import com.example.deanery.populators.SchedulePopulator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment implements RefreshInterface {

    private SwipeRefreshLayout swipeRefresh;
    private final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);
    //private final List<Day> days = getTemporaryTestData(); // TODO delete

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScheduleFragment() { }

    @Deprecated // TODO andlys change this to real data TODO delete
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
        return result;
    }

    public void pullSchedule(final RecyclerView recyclerView) {
        pullSchedule(recyclerView, "2", "Specialty test name");
    }

    public void pullSchedule(final RecyclerView recyclerView,
                             final String semester,
                             final String specialty) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                List<ScheduleItem> scheduleItems = bundle.getParcelableArrayList("scheduleItems");
                List<Discipline> allDisciplines = bundle.getParcelableArrayList("disciplines");
                List<Lecturer> allLecturers = bundle.getParcelableArrayList("lecturers");
                final List<Day> days = SchedulePopulator.populateScheduleDays(scheduleItems, allDisciplines, allLecturers, semester, specialty);
                Bundle b = new Bundle();
                b.putString("semester", semester);
                b.putString("specialty", specialty);
                getActivity().getIntent().putExtras(b);
                recyclerView.setAdapter(new ScheduleDayRecyclerViewAdapter(days, getActivity()));
                Log.i("schedule", "schedule accepted on ui");
            }
        };
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Log.i("schedule", "started loading schedule data");
                    Message msg = handler.obtainMessage();
                    final List<ScheduleItem> scheduleItems = client.getAllScheduleItems(MainActivity.getToken())
                            .execute()
                            .body()
                            .getData();
                    final List<Lecturer> lecturers = client.getAllLecturers(MainActivity.getToken())
                            .execute()
                            .body()
                            .getData();
                    final List<Discipline> disciplines = client.getAllDisciplines(MainActivity.getToken())
                            .execute()
                            .body()
                            .getData();
                    Log.i("schedule", "finished loading schedule data");
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("lecturers", new ArrayList(lecturers));
                    bundle.putParcelableArrayList("disciplines", new ArrayList(disciplines));
                    bundle.putParcelableArrayList("scheduleItems", new ArrayList(scheduleItems));
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    Log.i("schedule", "sent schedule data to ui");
                } catch (IOException e) {
                    Log.e("schedule","Failed to get all schedule items", e);
                }
            }
        };
        thread.start();
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
        pullSchedule(recyclerView);
        initSpinners(view, recyclerView);
        return view;
    }

    @Override
    public void refreshItems() {
        // TODO andlys
        Toast.makeText(getContext(), "Refreshed",
                Toast.LENGTH_LONG).show();
        swipeRefresh.setRefreshing(false);
    }

    private void initSpinners(final View view, final RecyclerView recyclerView) {
        final Spinner specialtiesSpinner = (Spinner) view.findViewById(R.id.spinner_specialties);
        final Call<DeaneryGetList<Specialty>> specialtiesCall = client.getAllSpecialties(MainActivity.getToken());
        specialtiesCall.enqueue(new Callback<DeaneryGetList<Specialty>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Specialty>> call, Response<DeaneryGetList<Specialty>> response) {
                final List<String> specialties = response.body().getData().
                        stream().map(Specialty::getName)
                        .sorted()
                        .collect(Collectors.toList());
                ArrayAdapter specialtiesAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, specialties);
                specialtiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                specialtiesSpinner.setAdapter(specialtiesAdapter);
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Specialty>> call, Throwable t) {
                Log.e("schedule","Failed to get all schedule items", t);
            }
        });
        final Spinner semestersSpinner = (Spinner) view.findViewById(R.id.spinner_semesters);
        final Call<DeaneryGetList<AcademicWeek>> semestersCall = client.getAllAcademicWeeks(MainActivity.getToken());
        semestersCall.enqueue(new Callback<DeaneryGetList<AcademicWeek>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<AcademicWeek>> call, Response<DeaneryGetList<AcademicWeek>> response) {
                final List<String> semesters = response.body().getData().
                        stream().map(item -> item.getSemester())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
                ArrayAdapter semestersAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, semesters);
                semestersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                semestersSpinner.setAdapter(semestersAdapter);
            }

            @Override
            public void onFailure(Call<DeaneryGetList<AcademicWeek>> call, Throwable t) {
                Log.e("schedule","Failed to get all schedule items", t);
            }
        });

        specialtiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String specialtySelected = parent.getItemAtPosition(position).toString();
                pullSchedule(recyclerView, semestersSpinner.getSelectedItem().toString(), specialtySelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        semestersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String semesterSelected = parent.getItemAtPosition(position).toString();
                pullSchedule(recyclerView, semesterSelected, specialtiesSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
