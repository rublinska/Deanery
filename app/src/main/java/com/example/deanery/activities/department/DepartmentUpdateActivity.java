package com.example.deanery.activities.department;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.deanery.CustomOnClickListener;
import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.GetStatus;
import com.example.deanery.dataModels.lecturer.Lecturer;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;
    Department departmentForUpdate;
    EditText departmentName;
    Button auditories, lecturers;

    List<Pair<Auditory, Boolean>> allAuditories = new ArrayList<>();
    List<Pair<Lecturer, Boolean>> allLecturers = new ArrayList<>();
    String[] auditoryList, lecturerList;
    boolean[] checkedAuditories, checkedLecturers;

    Button delete;
    Button cancel;
    Button updateDepartment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_update);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");

        departmentForUpdate = getIntent().getParcelableExtra("department");
        Log.i("lizatest", String.valueOf(departmentForUpdate.getId()));
        departmentName = (EditText) findViewById(R.id.department_name);

        delete = (Button) findViewById(R.id.delete);
        cancel = (Button) findViewById(R.id.cancel);
        updateDepartment = (Button) findViewById(R.id.update);

        departmentName.setText(departmentForUpdate.getName());

        auditories = findViewById(R.id.department_auditories);
        lecturers = findViewById(R.id.department_lecturers);

        Call<DeaneryGetList<Auditory>> getAllAuditories = client.getAllAuditories(token);
        getAllAuditories.enqueue(new Callback<DeaneryGetList<Auditory>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Auditory>> call, Response<DeaneryGetList<Auditory>> response) {
                Log.i("LizatestGEtAuditory", response.raw().toString());
                if (response.body().getData().size() > 0) {
                    auditoryList = new String[response.body().getData().size()];
                    checkedAuditories = new boolean[response.body().getData().size()];
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        allAuditories.add(Pair.create(response.body().getData().get(i), false));

                        auditoryList[i] = response.body().getData().get(i).toString();
                        if (response.body().getData().get(i).getDepartmentId() == departmentForUpdate.getId())
                            checkedAuditories[i] = true;
                        else
                            checkedAuditories[i] = false;
                    }
                    auditories.setOnClickListener(new CustomOnClickListener(auditories, auditoryList, checkedAuditories, allAuditories));
                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Auditory>> call, Throwable t) {
                Log.i("LizatestErrgetauditory", String.valueOf(call.isExecuted()));
            }
        });


        Call<DeaneryGetList<Lecturer>> getAllLecturers = client.getAllLecturers(token);
        getAllLecturers.enqueue(new Callback<DeaneryGetList<Lecturer>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Lecturer>> call, Response<DeaneryGetList<Lecturer>> response) {
                Log.i("LizatestGEtAuditory", String.valueOf(call.request()));
                if (response.body().getData().size() > 0) {
                    lecturerList = new String[response.body().getData().size()];
                    checkedLecturers = new boolean[response.body().getData().size()];
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        allLecturers.add(Pair.create(response.body().getData().get(i), false));

                        lecturerList[i] = response.body().getData().get(i).toString();
                        if (response.body().getData().get(i).getDepartmentId() == departmentForUpdate.getId())
                            checkedLecturers[i] = true;
                        else
                            checkedLecturers[i] = false;
                    }
                    lecturers.setOnClickListener(new CustomOnClickListener(lecturers, lecturerList, checkedLecturers, allLecturers));
                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Lecturer>> call, Throwable t) {
                Log.i("LizatestErrgetauditory", String.valueOf(call.request()));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<GetStatus> deleteDepartment = client.deleteDepartment(departmentForUpdate.getId(), getIntent().getStringExtra("token"));
                deleteDepartment.enqueue(new Callback<GetStatus>() {
                    @Override
                    public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                        Log.i("Lizatest", call.request().toString());
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
        updateDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departmentForUpdate.setName(departmentName.getText().toString());


                final Call<Department> updateLecturer = client.updateDepartment(departmentForUpdate.getId(),getIntent().getStringExtra("token"), departmentForUpdate);

                updateLecturer.enqueue(new Callback<Department>() {

                    @Override
                    public void onResponse(Call<Department> call, Response<Department> response) {
                        //    Log.i("LizatestError",response.raw().toString());
                        for (Pair<Auditory, Boolean> ab : allAuditories){

                            Auditory auditory = ab.first;
                            Log.i("lizatestdepid", departmentForUpdate.getId().toString());
                            if (ab.second)
                                auditory.setDepartmentId(departmentForUpdate.getId());
                            else
                                auditory.setDepartmentId(null);
                            final Call<Auditory> updateAuditory = client.updateAuditory(auditory.getId(), getIntent().getStringExtra("token"), auditory);
                            updateAuditory.enqueue(new Callback<Auditory>() {
                                @Override
                                public void onResponse(Call<Auditory> call, Response<Auditory> response) {
                                    Log.i("LizatestError",response.raw().toString());
                                }

                                @Override
                                public void onFailure(Call<Auditory> call, Throwable t) {
                                    Log.i("LizatestError",t.getMessage());
                                }
                            });
                        }
                        for (Pair<Lecturer, Boolean> ab : allLecturers){

                            Lecturer lecturer = ab.first;
                            Log.i("lizatestdepid", departmentForUpdate.getId().toString());
                            if (ab.second)
                                lecturer.setDepartmentId(departmentForUpdate.getId());
                            else
                                lecturer.setDepartmentId(null);
                            final Call<Lecturer> updateLecturer = client.updateLecturer(lecturer.getId(),getIntent().getStringExtra("token"), lecturer);
                            updateLecturer.enqueue(new Callback<Lecturer>() {
                                @Override
                                public void onResponse(Call<Lecturer> call, Response<Lecturer> response) {
                                    Log.i("LizatestError",response.raw().toString());
                                }
                                @Override
                                public void onFailure(Call<Lecturer> call, Throwable t) {
                                    Log.i("LizatestError",t.getMessage());
                                }
                            });
                        }
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Department> call, Throwable t) {
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
