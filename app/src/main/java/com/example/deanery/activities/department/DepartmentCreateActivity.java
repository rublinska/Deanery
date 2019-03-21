package com.example.deanery.activities.department;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deanery.CustomOnClickListener;
import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.auditory.GetAllAuditories;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.lecturer.GetAllLecturers;
import com.example.deanery.dataModels.lecturer.Lecturer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentCreateActivity extends AppCompatActivity {

    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);
    String token;
    TextView departmentName;
    Button auditories, lecturers;
    Button cancel;
    Button createNewDepartment;

    List<Pair<Auditory, Boolean>> allAuditories = new ArrayList<>();
    List<Pair<Lecturer, Boolean>> allLecturers = new ArrayList<>();
    String[] auditoryList, lecturerList;
    boolean[] checkedAuditories, checkedLecturers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_create);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");
        departmentName = findViewById(R.id.department_name);

        cancel = findViewById(R.id.cancel);
        createNewDepartment = findViewById(R.id.create);
        auditories = findViewById(R.id.department_auditories);
        lecturers = findViewById(R.id.department_lecturers);


        Call<GetAllAuditories> getAllAuditories = client.getAllAuditories(token);
        getAllAuditories.enqueue(new Callback<GetAllAuditories>() {
            @Override
            public void onResponse(Call<GetAllAuditories> call, Response<GetAllAuditories> response) {
                Log.i("LizatestGEtAuditory", response.raw().toString());
                if (response.body().getData().size() > 0) {
                    auditoryList = new String[response.body().getData().size()];
                    checkedAuditories = new boolean[response.body().getData().size()];
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        allAuditories.add(Pair.create(response.body().getData().get(i), false));

                        auditoryList[i] = response.body().getData().get(i).toString();
                        checkedAuditories[i] = false;
                    }
                    auditories.setOnClickListener(new CustomOnClickListener(auditories, auditoryList, checkedAuditories, allAuditories));
                }
            }

            @Override
            public void onFailure(Call<GetAllAuditories> call, Throwable t) {
                Log.i("LizatestErrgetauditory", String.valueOf(call.isExecuted()));
            }
        });


        Call<GetAllLecturers> getAllLecturers = client.getAllLecturers(token);
        getAllLecturers.enqueue(new Callback<GetAllLecturers>() {
            @Override
            public void onResponse(Call<GetAllLecturers> call, Response<GetAllLecturers> response) {
                Log.i("LizatestGEtAuditory", String.valueOf(call.request()));
                if (response.body().getData().size() > 0) {
                    lecturerList = new String[response.body().getData().size()];
                    checkedLecturers = new boolean[response.body().getData().size()];
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        allLecturers.add(Pair.create(response.body().getData().get(i), false));

                        lecturerList[i] = response.body().getData().get(i).toString();
                        checkedLecturers[i] = false;
                    }
                    lecturers.setOnClickListener(new CustomOnClickListener(lecturers, lecturerList, checkedLecturers, allLecturers));
                }
            }

            @Override
            public void onFailure(Call<GetAllLecturers> call, Throwable t) {
                Log.i("LizatestErrgetauditory", String.valueOf(call.request()));
            }
        });
        /*
        ArrayAdapter<Auditory> auditoryAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, auditoryList);
     //   auditoryAdapter.setC(ListView.CHOICE_MODE_MULTIPLE);
        auditoryAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_view);*/


/*
        Call<GetAllLecturers> getAllLecturers = client.getAllLecturers(token);
        getAllLecturers.enqueue(new Callback<GetAllLecturers>() {
            @Override
            public void onResponse(Call<GetAllLecturers> call, Response<GetAllLecturers> response) {
                Log.i("LizatestGEtLecturer", response.body().getData().get(0).getFullName());
                Log.i("LizatestGEtLecturer", response.raw().toString());
                lecturerList = response.body().getData();
            }

            @Override
            public void onFailure(Call<GetAllLecturers> call, Throwable t) {
                Log.i("LizatestErrgetlecturers", String.valueOf(call.isExecuted()));
            }
        });
        ArrayAdapter<Lecturer> lecturerAdapter = new ArrayAdapter<Lecturer>(this, android.R.layout.simple_list_item_multiple_choice, lecturerList);
        //   lecturers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lecturerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_view);
        lecturers.setAdapter(lecturerAdapter);*/


        createNewDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Integer[] departmentID = new Integer[1];
                Department newDepartment = new Department("" + departmentName.getText());
             //   newDepartment.setAuditories(addAuditories);
             //   newDepartment.setLecturers(addLecturers);

                //    Log.i("Lizatest", getIntent().getStringExtra("token"));
                final Call<Department> createDepartment = client.createDepartment(token, newDepartment);
                createDepartment.enqueue(new Callback<Department>() {
                    @Override
                    public void onResponse(Call<Department> call, Response<Department> response) {
                        //        Log.i("Lizatest", response.raw().toString());
                        departmentID[0] = response.body().getId();
                    }
                    @Override
                    public void onFailure(Call<Department> call, Throwable t) {
                        //       Log.i("Lizatest", t.getMessage());
                    }
                });

                List<Auditory> addAuditories = new ArrayList<>();
                for (Pair<Auditory, Boolean> ab : allAuditories)
                    if (ab.second) {
                        Auditory auditory = ab.first;
                        auditory.setDepartmentId(departmentID[0]);
                        final Call<Auditory> updateAuditory = client.updateAuditory(auditory.getId(), getIntent().getStringExtra("token"), auditory);
                        updateAuditory.enqueue(new Callback<Auditory>() {
                            @Override
                            public void onResponse(Call<Auditory> call, Response<Auditory> response) {
                                //    Log.i("LizatestError",response.raw().toString());
                            }

                            @Override
                            public void onFailure(Call<Auditory> call, Throwable t) {
                                //    Log.i("LizatestError",t.getMessage());
                            }
                        });
                    }

                List<Lecturer> addLecturers = new ArrayList<>();
                for (Pair<Lecturer, Boolean> ab : allLecturers)
                    if (ab.second) {
                        Lecturer lecturer = ab.first;
                        lecturer.setDepartmentId(departmentID[0]);
                        final Call<Lecturer> updateLecturer = client.updateLecturer(lecturer.getId(),getIntent().getStringExtra("token"), lecturer);
                        updateLecturer.enqueue(new Callback<Lecturer>() {
                            @Override
                            public void onResponse(Call<Lecturer> call, Response<Lecturer> response) {
                                //    Log.i("LizatestError",response.raw().toString());
                            }
                            @Override
                            public void onFailure(Call<Lecturer> call, Throwable t) {
                                //    Log.i("LizatestError",t.getMessage());
                            }
                        });
                    }
                closeActivity();

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
