package com.example.deanery.activities.student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.example.deanery.dataModels.specialty.GetAllSpecialties;
import com.example.deanery.dataModels.specialty.Specialty;
import com.example.deanery.dataModels.student.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentCreateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;
    EditText fullName;
    EditText phone;
    EditText startUni;
    EditText endUni;
    EditText endReason;
    Spinner specialty;
    Button cancel;
    Button createNewStudent;

    List<Specialty> specialtiesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");
        fullName = (EditText) findViewById(R.id.student_full_name);
        phone = (EditText) findViewById(R.id.student_phone);
        startUni = (EditText) findViewById(R.id.student_startUni);
        endUni = (EditText) findViewById(R.id.student_endUni);
        endReason = findViewById(R.id.student_endReason);
        cancel = (Button) findViewById(R.id.cancel);
        createNewStudent = (Button) findViewById(R.id.create);
        specialtiesArray = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, specialtiesArray);


        Call<GetAllSpecialties> getAllSpecialties = client.getAllSpecialties(token);
        getAllSpecialties.enqueue(new Callback<GetAllSpecialties>() {
            @Override
            public void onResponse(Call<GetAllSpecialties> call, Response<GetAllSpecialties> response) {
                Log.i("LizatestCreStu", response.body().getData().get(0).getName());
             //   Log.i("LizatestCreStu", response.raw().toString());
                specialtiesArray = response.body().getData();
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                specialty.setAdapter(adapter);
                specialty.setSelection(0);
            }

            @Override
            public void onFailure(Call<GetAllSpecialties> call, Throwable t) {
             //   Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });


        createNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Specialty newSpecialty = (Specialty) specialty.getSelectedItem();
                Student newStudent = new Student(fullName.getText().toString(),startUni.getText().toString(),
                        endUni.getText().toString(), phone.getText().toString(), endReason.getText().toString(), newSpecialty.getId());


            //    Log.i("Lizatest", getIntent().getStringExtra("token"));
                final Call<Student> createStudent = client.createStudent(token, newStudent);

                createStudent.enqueue(new Callback<Student>() {

                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                //        Log.i("Lizatest", response.raw().toString());

                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
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
