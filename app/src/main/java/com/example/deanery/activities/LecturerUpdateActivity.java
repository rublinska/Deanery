package com.example.deanery.activities;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.Department;
import com.example.deanery.dataModels.GetLecturer;
import com.example.deanery.dataModels.Lecturer;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);

    EditText full_name;
    EditText department;
    EditText phone;
    EditText position;
    Button cancel;
    Button updateLecturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_details);
     //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);

        full_name = (EditText) findViewById(R.id.lecturer_full_name);
        department = (EditText) findViewById(R.id.lecturer_department);
        phone = (EditText) findViewById(R.id.lecturer_phone);
        position = (EditText) findViewById(R.id.lecturer_position);
        cancel = (Button) findViewById(R.id.cancel);
        updateLecturer = (Button) findViewById(R.id.submit);

        full_name.setText(getIntent().getStringExtra("fullName"));
        department.setText(getIntent().getStringExtra("department"));
        phone.setText(getIntent().getStringExtra("phoneNumber"));
        position.setText(getIntent().getStringExtra("position"));


        updateLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lecturer lecturerForUpdate = new Lecturer(full_name.getText().toString(),position.getText().toString(),phone.getText().toString(), 1/* TODO get departmentID by name *//*Integer.parseInt(department.getText().toString())*/);
                final Call<Lecturer> updateLecturer = client.updateLecturer(getIntent().getIntExtra("lecturerID", -1), getIntent().getStringExtra("token"), lecturerForUpdate);

                updateLecturer.enqueue(new Callback<Lecturer>() {

                    @Override
                    public void onResponse(Call<Lecturer> call, Response<Lecturer> response) {
                        Log.i("Lizatest", call.request().url().toString());
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Lecturer> call, Throwable t) {
                        closeActivity();
                    }
                });
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }
    public void closeActivity() {
        this.finish();
    }
}
