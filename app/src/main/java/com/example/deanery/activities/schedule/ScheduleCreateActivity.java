package com.example.deanery.activities.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.activities.MainActivity;
import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.example.deanery.dataModels.schedule.AcademicWeek;
import com.example.deanery.dataModels.schedule.ClassTime;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.schedule.ScheduleItemDto;
import com.example.deanery.dataModels.schedule.TimeSlot;
import com.example.deanery.dataModels.schedule.UniversityClass;
import com.example.deanery.dataModels.student.UniversityGroup;

import java.util.List;
import java.util.function.Predicate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleCreateActivity extends AppCompatActivity {

    private final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_create);
        final Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        Spinner groupSpinner = findViewById(R.id.schedule_group_spinner);
        Spinner weekSpinner = findViewById(R.id.schedule_week_spinner);
        Spinner auditorySpinner = findViewById(R.id.schedule_auditory_spinner);
        Spinner universityClassSpinner = findViewById(R.id.schedule_university_class_spinner);
        Spinner classTimesSpinner = findViewById(R.id.schedule_time_spinner);
        Spinner weekDaySpinner = findViewById(R.id.schedule_week_day_spinner);
        setUpSpinnerValues(classTimesSpinner,
                client.getAllClassTimes(MainActivity.getToken()));
        setUpSpinnerValues(universityClassSpinner,
                client.getAllUniversityClasses(MainActivity.getToken()));
        setUpSpinnerValues(groupSpinner,
                client.getAllGroups(MainActivity.getToken()));
        setUpSpinnerValues(auditorySpinner,
                client.getAllAuditories(MainActivity.getToken()));
        setUpSpinnerValues(weekSpinner,
                client.getAllAcademicWeeks(MainActivity.getToken()));

        Button createNewScheduleItem = findViewById(R.id.create);
        createNewScheduleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int groupId = ((UniversityGroup) groupSpinner.getSelectedItem()).getId();
                int weekId = ((AcademicWeek) weekSpinner.getSelectedItem()).getId();
                int auditoryId = ((Auditory) auditorySpinner.getSelectedItem()).getId();
                int uniClassId = ((UniversityClass) universityClassSpinner.getSelectedItem()).getId();
                int classTimeId = ((ClassTime) classTimesSpinner.getSelectedItem()).getId();
                int weekDay = Integer.valueOf((String) weekDaySpinner.getSelectedItem());
                final ScheduleItemDto newItem = new ScheduleItemDto(classTimeId, weekId, auditoryId, uniClassId, groupId, weekDay);
                final Call<ScheduleItem> createLecturer = client.createScheduleItem(MainActivity.getToken(), newItem);
                createLecturer.enqueue(new Callback<ScheduleItem>() {
                    @Override
                    public void onResponse(Call<ScheduleItem> call, Response<ScheduleItem> response) {
                        Log.i("scheduleCreate", response.raw().toString());
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<ScheduleItem> call, Throwable t) {
                        Log.i("scheduleCreate", t.getMessage());
                        closeActivity();
                    }
                });
            }
        });
    }

    private <T> void setUpSpinnerValues(final Spinner spinner,
                                        final Call<DeaneryGetList<T>> call) {
        call.enqueue(new Callback<DeaneryGetList<T>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<T>> call, Response<DeaneryGetList<T>> response) {

                List<T> array = response.body().getData();
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, array);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<DeaneryGetList<T>> call, Throwable t) {
                Log.i("error", String.valueOf(call.isExecuted()));
            }
        });
    }

    public void closeActivity() {
        setResult(10001);
        this.finish();
    }

}
