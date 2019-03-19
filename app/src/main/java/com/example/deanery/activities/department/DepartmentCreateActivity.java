package com.example.deanery.activities.department;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.auditory.GetAllAuditories;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.lecturer.GetAllLecturers;
import com.example.deanery.dataModels.lecturer.Lecturer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentCreateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;
    TextView departmentName;
    ListView auditories, lecturers;
    Button cancel;
    Button createNewDepartment;

    List<Auditory> auditoryList, checkedAuditories;
    List<Lecturer> lecturerList, checkedLecturers;

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

        auditoryList = new ArrayList<>();
        Call<GetAllAuditories> getAllAuditories = client.getAllAuditories(token);
        getAllAuditories.enqueue(new Callback<GetAllAuditories>() {
            @Override
            public void onResponse(Call<GetAllAuditories> call, Response<GetAllAuditories> response) {
                Log.i("LizatestGEtAuditory", response.body().getData().get(0).getNumber());
                Log.i("LizatestGEtAuditory", response.raw().toString());
                auditoryList = response.body().getData();
            }

            @Override
            public void onFailure(Call<GetAllAuditories> call, Throwable t) {
                Log.i("LizatestErrgetauditory", String.valueOf(call.isExecuted()));
            }
        });
        ArrayAdapter<Auditory> auditoryAdapter = new ArrayAdapter<Auditory>(this, android.R.layout.simple_list_item_multiple_choice, auditoryList);
        auditories.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        auditories.setAdapter(auditoryAdapter);


        lecturerList = new ArrayList<>();
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
        lecturers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lecturers.setAdapter(lecturerAdapter);


        createNewDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int cntChoice = auditories.getCount();
                SparseBooleanArray sparseBooleanArray = auditories.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        checkedAuditories.add((Auditory) auditories.getItemAtPosition(i));
                    }
                }
                cntChoice = lecturers.getCount();
                sparseBooleanArray = lecturers.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        checkedLecturers.add((Lecturer) lecturers.getItemAtPosition(i));
                    }
                }

                Department newDepartment = new Department((String) departmentName.getText());
                newDepartment.setAuditories(checkedAuditories);
                newDepartment.setLecturers(checkedLecturers);

            //    Log.i("Lizatest", getIntent().getStringExtra("token"));
                final Call<Department> createDepartment = client.createDepartment(token, newDepartment);

                createDepartment.enqueue(new Callback<Department>() {
                    @Override
                    public void onResponse(Call<Department> call, Response<Department> response) {
                //        Log.i("Lizatest", response.raw().toString());
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Department> call, Throwable t) {
                 //       Log.i("Lizatest", t.getMessage());
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
