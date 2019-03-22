package com.example.deanery.activities.student;

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
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.example.deanery.dataModels.specialty.GetAllSpecialties;
import com.example.deanery.dataModels.specialty.Specialty;
import com.example.deanery.dataModels.student.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;

    Student studentForUpdate;
    EditText fullName;
    EditText phone;
    EditText startUni;
    EditText endUni;
    EditText endReason;
    Spinner specialty;

    Button delete;
    Button cancel;
    Button updateStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update);
     //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");

        studentForUpdate = getIntent().getParcelableExtra("student");
        Log.i("lizatest", String.valueOf(studentForUpdate.getId()));
        fullName = (EditText) findViewById(R.id.student_full_name);
        phone = (EditText) findViewById(R.id.student_phone);
        startUni = (EditText) findViewById(R.id.student_startUni);
        endUni = (EditText) findViewById(R.id.student_endUni);
        endReason = findViewById(R.id.student_endReason);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        updateStudent = findViewById(R.id.update);

        fullName.setText(studentForUpdate.getName());
        startUni.setText(studentForUpdate.getStartUniversity());
        endUni.setText(studentForUpdate.getEndUniversity());
        endReason.setText(studentForUpdate.getEndReason());

        Call<GetAllSpecialties> getAllSpecialties = client.getAllSpecialties(token);
        getAllSpecialties.enqueue(new Callback<GetAllSpecialties>() {
            @Override
            public void onResponse(Call<GetAllSpecialties> call, Response<GetAllSpecialties> response) {
            //    Log.i("LizatestUpdStudent", response.body().getData().get(0).getName());
            //    Log.i("LizatestUpdStudent", response.raw().toString());
                List<Specialty> specialtiesArray = response.body().getData();
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, specialtiesArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                specialty.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetAllSpecialties> call, Throwable t) {
            //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<GetStatus> deleteStudent = client.deleteStudent(studentForUpdate.getId(), token);
                deleteStudent.enqueue(new Callback<GetStatus>() {
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

        updateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentForUpdate.setName(fullName.getText().toString());
                studentForUpdate.setStartUniversity(startUni.getText().toString());
                studentForUpdate.setPhone(phone.getText().toString());
                studentForUpdate.setEndReason(endReason.getText().toString());
                studentForUpdate.setEndUniversity(endUni.getText().toString());
                Specialty newSpecialty = (Specialty) specialty.getSelectedItem();
                studentForUpdate.setSpecialtyId(newSpecialty.getId());

                final Call<Student> updateStudent = client.updateStudent(studentForUpdate.getId(),getIntent().getStringExtra("token"), studentForUpdate);

                updateStudent.enqueue(new Callback<Student>() {

                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                    //    Log.i("LizatestError",response.raw().toString());
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
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
