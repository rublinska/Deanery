package com.example.deanery.activities.lecturer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.department.GetAllDepartments;
import com.example.deanery.dataModels.lecturer.Lecturer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerCreateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;
    TextView fullName;
    Spinner department;
    TextView phone;
    TextView position;
    Button cancel;
    Button createNewLecturer;

    List<Department> departmentsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_create);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");
        fullName = (TextView) findViewById(R.id.department_name);
        department = (Spinner) findViewById(R.id.department);
        phone = (TextView) findViewById(R.id.phone_number);
        position = (TextView) findViewById(R.id.position);
        cancel = (Button) findViewById(R.id.cancel);
        createNewLecturer = (Button) findViewById(R.id.create);
        departmentsArray = new ArrayList<>();
        final ArrayAdapter[] adapter = {new ArrayAdapter(this, android.R.layout.simple_spinner_item, departmentsArray)};
        adapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(adapter[0]);

        Call<GetAllDepartments> getAllDepartments = client.getAllDepartments(token);
        getAllDepartments.enqueue(new Callback<GetAllDepartments>() {
            @Override
            public void onResponse(Call<GetAllDepartments> call, Response<GetAllDepartments> response) {
                Log.i("LizatestCreteLecturer", response.body().getData().get(0).getName());
                Log.i("LizatestCreteLecturer", response.raw().toString());
                departmentsArray = response.body().getData();
                adapter[0] = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, departmentsArray);
                adapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                department.setAdapter(adapter[0]);
            }

            @Override
            public void onFailure(Call<GetAllDepartments> call, Throwable t) {
                Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });


        createNewLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Department departmentId = (Department) department.getSelectedItem();
                Lecturer newLecturer = new Lecturer(fullName.getText().toString(),position.getText().toString(),phone.getText().toString(),Integer.parseInt(departmentId.getId().toString()));

            //    Log.i("Lizatest", getIntent().getStringExtra("token"));
                final Call<Lecturer> createLecturer = client.createLecturer(token, newLecturer);

                createLecturer.enqueue(new Callback<Lecturer>() {

                    @Override
                    public void onResponse(Call<Lecturer> call, Response<Lecturer> response) {
                //        Log.i("Lizatest", response.raw().toString());

                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Lecturer> call, Throwable t) {
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
