package com.example.deanery.activities;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.GetLecturer;
import com.example.deanery.dataModels.Lecturer;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LecturerDetailsActivity extends AppCompatActivity {

    Lecturer lecturer;
    TextView full_name;
    TextView department;
    TextView phone;
    TextView position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_details);
     //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);

        full_name = (TextView) findViewById(R.id.lecturer_full_name);
        department = (TextView) findViewById(R.id.lecturer_department);
        phone = (TextView) findViewById(R.id.lecturer_phone);
        position = (TextView) findViewById(R.id.lecturer_position);


        final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
        final Call<GetLecturer> getLecturer = client.getLecturer(getIntent().getStringExtra("token"), getIntent().getIntExtra("lecturerID", 1));

        getLecturer.enqueue(new Callback<GetLecturer>() {

            @Override
            public void onResponse(Call<GetLecturer> call, Response<GetLecturer> response) {
                lecturer = response.body().getLecturer();

                full_name.setText(lecturer.getFullName());
                phone.setText(lecturer.getPhoneNumber());
                position.setText(lecturer.getPosition());
                department.setText(lecturer.getDepartment().getName());

            }

            @Override
            public void onFailure(Call<GetLecturer> call, Throwable t) {

            }
        });
    }
}
