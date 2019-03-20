package com.example.deanery.activities.discipline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.discipline.Discipline;
import com.example.deanery.dataModels.discipline.GetAllDisciplines;
import com.example.deanery.dataModels.specialty.GetAllSpecialties;
import com.example.deanery.dataModels.specialty.Specialty;
import com.example.deanery.dataModels.student.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisciplineCreateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;

    Discipline disciplineForUpdate;
    EditText fullName;
    EditText selfWorkTime;
    Spinner specialtySpinner;
    Spinner preDisciplineSpinner;
    List<Specialty> specialtiesArray;
    List<Discipline> disciplinesArray;

    Button cancel;
    Button createNewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");
        fullName = findViewById(R.id.discipline_name);
        selfWorkTime = findViewById(R.id.discipline_selfWorkTime);
        specialtySpinner = findViewById(R.id.discipline_specialty);
        preDisciplineSpinner = findViewById(R.id.discipline_preDiscipline);
        cancel = (Button) findViewById(R.id.cancel);
        createNewStudent = (Button) findViewById(R.id.create);

        Call<GetAllSpecialties> getAllSpecialties = client.getAllSpecialties(token);
        getAllSpecialties.enqueue(new Callback<GetAllSpecialties>() {
            @Override
            public void onResponse(Call<GetAllSpecialties> call, Response<GetAllSpecialties> response) {
                //    Log.i("LizatestUpdDiscipline", response.body().getData().get(0).getName());
                //    Log.i("LizatestUpdDiscipline", response.raw().toString());
                specialtiesArray = response.body().getData();
                ArrayAdapter specialtiesAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, specialtiesArray);
                specialtiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                specialtySpinner.setAdapter(specialtiesAdapter);
            }
            @Override
            public void onFailure(Call<GetAllSpecialties> call, Throwable t) {
                //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });


        Call<GetAllDisciplines> getAllDisciplines = client.getAllDisciplines(token);
        getAllDisciplines.enqueue(new Callback<GetAllDisciplines>() {
            @Override
            public void onResponse(Call<GetAllDisciplines> call, Response<GetAllDisciplines> response) {
                //    Log.i("LizatestUpdDiscipline", response.body().getData().get(0).getName());
                //    Log.i("LizatestUpdDiscipline", response.raw().toString());
                disciplinesArray = response.body().getData();
                ArrayAdapter disciplinesAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, disciplinesArray);
                disciplinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                preDisciplineSpinner.setAdapter(disciplinesAdapter);
            }
            @Override
            public void onFailure(Call<GetAllDisciplines> call, Throwable t) {
                //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });

        createNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Specialty newSpecialty = (Specialty) specialtySpinner.getSelectedItem();
                Discipline newPreDiscipline = (Discipline) preDisciplineSpinner.getSelectedItem();

                Discipline newDiscipline = new Discipline(fullName.getText().toString(),Integer.valueOf(selfWorkTime.getText().toString()));
                newDiscipline.setPreDisciplineId(newPreDiscipline.getId());
                newDiscipline.setSpecialtyId(newSpecialty.getId());
            //    Log.i("Lizatest", getIntent().getStringExtra("token"));
                final Call<Discipline> createDiscipline = client.createDiscipline(token, newDiscipline);

                createDiscipline.enqueue(new Callback<Discipline>() {

                    @Override
                    public void onResponse(Call<Discipline> call, Response<Discipline> response) {
                //        Log.i("Lizatest", response.raw().toString());

                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Discipline> call, Throwable t) {
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
