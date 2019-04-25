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
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.schedule.TimeSlot;

import java.util.List;
import java.util.function.Predicate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleUpdateActivity extends AppCompatActivity {

    private final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_update);
        final Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });

        final TimeSlot timeSlot = getIntent().getParcelableExtra("schedule_time_slot");
        final ScheduleItem scheduleItem = timeSlot.getScheduleItem();

        Spinner groupSpinner = findViewById(R.id.schedule_group_spinner);
        Spinner weekSpinner = findViewById(R.id.schedule_week_spinner);
        Spinner auditorySpinner = findViewById(R.id.schedule_auditory_spinner);
        Spinner universityClass = findViewById(R.id.schedule_university_class_spinner);
        Spinner classTimesSpinner = findViewById(R.id.schedule_time_spinner);
        setUpSpinnerValues(classTimesSpinner,
                classTime -> classTime.getId().equals(scheduleItem.getClassTime().getId()),
                client.getAllClassTimes(MainActivity.getToken()));
        setUpSpinnerValues(universityClass,
                uniClass -> uniClass.getId().equals(scheduleItem.getUniversityClass().getId()),
                client.getAllUniversityClasses(MainActivity.getToken()));
        setUpSpinnerValues(groupSpinner,
                group -> group.getId().equals(scheduleItem.getGroup().getId()),
                client.getAllGroups(MainActivity.getToken()));
        setUpSpinnerValues(auditorySpinner,
                auditory -> auditory.getId().equals(scheduleItem.getAuditory().getId()),
                client.getAllAuditories(MainActivity.getToken()));
        setUpSpinnerValues(weekSpinner,
                academicWeek -> academicWeek.getId().equals(scheduleItem.getAcademicWeek().getId()),
                client.getAllAcademicWeeks(MainActivity.getToken()));
    }

    private <T> void setUpSpinnerValues(final Spinner spinner,
                                        final Predicate<T> predicate,
                                        final Call<DeaneryGetList<T>> call) {
        call.enqueue(new Callback<DeaneryGetList<T>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<T>> call, Response<DeaneryGetList<T>> response) {

                List<T> array = response.body().getData();
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, array);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                for (int i = 0; i < adapter.getCount(); i++) {
                    T disc = (T) adapter.getItem(i);
                    if(predicate.test(disc)) {
                        spinner.setSelection(i);
                    }
                }
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
