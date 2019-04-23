package com.example.deanery.activities.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deanery.R;
import com.example.deanery.dataModels.schedule.TimeSlot;

public class ScheduleUpdateActivity extends AppCompatActivity {

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
        final String semester = getIntent().getStringExtra("semester");
        final String specialty = getIntent().getStringExtra("specialty");

        EditText timeInterval = findViewById(R.id.schedule_time);
        EditText discipline = findViewById(R.id.schedule_discipline);
        EditText group = findViewById(R.id.schedule_group);
        EditText lecturer = findViewById(R.id.schedule_lecturer);
        EditText auditory = findViewById(R.id.schedule_auditory);
        EditText week = findViewById(R.id.schedule_day_of_week);

        timeInterval.setText(timeSlot.getTimeInterval());
        discipline.setText(timeSlot.getDiscipline());
        group.setText(timeSlot.getGroup());
        lecturer.setText(timeSlot.getLecturer());
        auditory.setText(timeSlot.getAuditory());
        week.setText(timeSlot.getWeek());
    }

    public void closeActivity() {
        setResult(10001);
        this.finish();
    }

}
