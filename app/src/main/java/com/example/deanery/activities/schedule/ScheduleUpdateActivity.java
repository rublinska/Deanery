package com.example.deanery.activities.schedule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.deanery.R;

public class ScheduleUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_update);
    }

    public void closeActivity() {
        setResult(10001);
        this.finish();
    }

}
