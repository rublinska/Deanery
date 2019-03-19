package com.example.deanery.activities.lecturer;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.GetStatus;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.department.GetAllDepartments;
import com.example.deanery.dataModels.lecturer.Lecturer;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;

    Lecturer lecturerForUpdate;
    EditText fullName;
    Spinner departmentSpinner;
    EditText phone;
    EditText position;
    Button delete;
    Button cancel;
    Button updateLecturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_update);
     //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token  =getIntent().getStringExtra("token");
        lecturerForUpdate = getIntent().getParcelableExtra("departmentSpinner");
        Log.i("lizatest", String.valueOf(lecturerForUpdate.getId()));
        fullName = findViewById(R.id.department_name);
        departmentSpinner = findViewById(R.id.lecturer_department);
        phone = findViewById(R.id.lecturers_num);
        position = findViewById(R.id.auditories_num);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        updateLecturer = findViewById(R.id.update);

        fullName.setText(lecturerForUpdate.getFullName());
        phone.setText(lecturerForUpdate.getPhoneNumber());
        position.setText(lecturerForUpdate.getPosition());

        Call<GetAllDepartments> getAllDepartments = client.getAllDepartments(token);
        getAllDepartments.enqueue(new Callback<GetAllDepartments>() {
            @Override
            public void onResponse(Call<GetAllDepartments> call, Response<GetAllDepartments> response) {

                List<Department> departmentsArray = response.body().getData();
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, departmentsArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                departmentSpinner.setAdapter(adapter);
                for (int i = 0; i < adapter.getCount(); i++) {

                //    Log.i("LizatestCreateLecturer", String.valueOf(((Department) adapter.getItem(i)).getName().equals(lecturerForUpdate.getDepartment().getName())));
                //    Log.i("LizatestCreateLecturer", String.valueOf(adapter.getItem(position) == lecturerForUpdate.getDepartment().getName()));

                    if(((Department) adapter.getItem(i)).getName().equals(lecturerForUpdate.getDepartment().getName())) {
                        departmentSpinner.setSelection(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDepartments> call, Throwable t) {
                Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<GetStatus> deleteLecturer = client.deleteLecturer(lecturerForUpdate.getId(), token);
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
                Department newDepartment = (Department) departmentSpinner.getSelectedItem();
                lecturerForUpdate.setDepartmentId(newDepartment.getId());
                lecturerForUpdate.setDepartment(newDepartment);

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
