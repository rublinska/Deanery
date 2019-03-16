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
import com.example.deanery.dataModels.GetStatus;
import com.example.deanery.dataModels.Lecturer;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    Lecturer lecturerForUpdate;
    EditText fullName;
    EditText department;
    EditText phone;
    EditText position;
    Button delete;
    Button cancel;
    Button updateLecturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_details);
     //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);

        lecturerForUpdate = getIntent().getParcelableExtra("department");
        Log.i("lizatest", String.valueOf(lecturerForUpdate.getId()));
        fullName = (EditText) findViewById(R.id.department_name);
        department = (EditText) findViewById(R.id.lecturer_department);
        phone = (EditText) findViewById(R.id.lecturers_num);
        position = (EditText) findViewById(R.id.auditories_num);
        delete = (Button) findViewById(R.id.delete);
        cancel = (Button) findViewById(R.id.cancel);
        updateLecturer = (Button) findViewById(R.id.submit);

        fullName.setText(lecturerForUpdate.getFullName());
        department.setText(lecturerForUpdate.getDepartment().getName());
        phone.setText(lecturerForUpdate.getPhoneNumber());
        position.setText(lecturerForUpdate.getPosition());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<GetStatus> deleteLecturer = client.deleteLecturer(lecturerForUpdate.getId(), getIntent().getStringExtra("token"));
                deleteLecturer.enqueue(new Callback<GetStatus>() {
                    @Override
                    public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                    //    Log.i("Lizatest", response.raw().toString());
                        closeActivity();
                    }
                    @Override
                    public void onFailure(Call<GetStatus> call, Throwable t) {
                    //    Log.i("LizatestError",t.getMessage());
                        closeActivity();
                    }
                });
            }
        });

        updateLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lecturerForUpdate.setFullName(fullName.getText().toString());
                lecturerForUpdate.setPosition(position.getText().toString());
                lecturerForUpdate.setPhoneNumber(phone.getText().toString());

        //todo        lecturerForUpdate.setDepartment(Integer.parseInt(department.getText().toString()));
                final Call<Lecturer> updateLecturer = client.updateLecturer(lecturerForUpdate.getId(),getIntent().getStringExtra("token"),lecturerForUpdate);

                updateLecturer.enqueue(new Callback<Lecturer>() {

                    @Override
                    public void onResponse(Call<Lecturer> call, Response<Lecturer> response) {
                    //    Log.i("LizatestError",response.raw().toString());
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Lecturer> call, Throwable t) {
                    //    Log.i("LizatestError",t.getMessage());
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
        setResult(10001);
        this.finish();
    }
}
