package com.example.deanery.activities.department;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.lecturer.GetStatus;
import com.example.deanery.dataModels.lecturer.Lecturer;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    Department departmentForUpdate;
    EditText departmentName;

    Button delete;
    Button cancel;
    Button updateDepartment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_update);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);

        departmentForUpdate = getIntent().getParcelableExtra("department");
        Log.i("lizatest", String.valueOf(departmentForUpdate.getId()));
        departmentName = (EditText) findViewById(R.id.department_name);

        delete = (Button) findViewById(R.id.delete);
        cancel = (Button) findViewById(R.id.cancel);
        updateDepartment = (Button) findViewById(R.id.create);

        departmentName.setText(departmentForUpdate.getName());


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<GetStatus> deleteLecturer = client.deleteLecturer(departmentForUpdate.getId(), getIntent().getStringExtra("token"));
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
/*
        updateDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departmentForUpdate.setName(departmentName.getText().toString());

                //todo        departmentForUpdate.setDepartment(Integer.parseInt(department.getText().toString()));
                final Call<Lecturer> updateLecturer = client.updateLecturer(departmentForUpdate.getId(),getIntent().getStringExtra("token"), departmentForUpdate);

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
        });*/

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
