package com.example.deanery.activities.discipline;

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
import com.example.deanery.dataModels.discipline.GetAllDisciplines;
import com.example.deanery.dataModels.specialty.GetAllSpecialties;
import com.example.deanery.dataModels.specialty.Specialty;
import com.example.deanery.dataModels.discipline.Discipline;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisciplineUpdateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;

    Discipline disciplineForUpdate;
    EditText fullName;
    EditText selfWorkTime;
    Spinner specialty;
    Spinner preDiscipline;
    List<Specialty> specialtiesArray;
    List<Discipline> disciplinesArray;

    Button delete;
    Button cancel;
    Button updateDiscipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_update);
     //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");

        disciplineForUpdate = getIntent().getParcelableExtra("discipline");
        Log.i("lizatest", String.valueOf(disciplineForUpdate.getId()));
        fullName = findViewById(R.id.discipline_name);
        selfWorkTime = findViewById(R.id.discipline_selfWorkTime);
        specialty = findViewById(R.id.discipline_specialty);
        preDiscipline = findViewById(R.id.discipline_preDiscipline);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        updateDiscipline = findViewById(R.id.update);

        fullName.setText(disciplineForUpdate.getName());
        selfWorkTime.setText(disciplineForUpdate.getSelfWorkTime());

        specialtiesArray = new ArrayList<>();
        final ArrayAdapter[] specialtiesAdapter = {new ArrayAdapter(this, android.R.layout.simple_spinner_item, specialtiesArray)};
        specialtiesAdapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialty.setAdapter(specialtiesAdapter[0]);

        Call<GetAllSpecialties> getAllSpecialties = client.getAllSpecialties(token);
        getAllSpecialties.enqueue(new Callback<GetAllSpecialties>() {
            @Override
            public void onResponse(Call<GetAllSpecialties> call, Response<GetAllSpecialties> response) {
            //    Log.i("LizatestUpdDiscipline", response.body().getData().get(0).getName());
            //    Log.i("LizatestUpdDiscipline", response.raw().toString());
                specialtiesArray = response.body().getData();/*
                specialtiesAdapter[0] = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, specialtiesArray);
                specialtiesAdapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                specialty.setAdapter(specialtiesAdapter[0]);
            }

            @Override
            public void onFailure(Call<GetAllSpecialties> call, Throwable t) {
            //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });
        specialty.setSelection(specialtiesAdapter[0].getPosition(disciplineForUpdate.getSpecialty()));

        disciplinesArray = new ArrayList<>();
        final ArrayAdapter[] disciplinesAdapter = {new ArrayAdapter(this, android.R.layout.simple_spinner_item, disciplinesArray)};
        disciplinesAdapter[0].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialty.setAdapter(disciplinesAdapter[0]);

        Call<GetAllDisciplines> getAllDisciplines = client.getAllDisciplines(token);
        getAllDisciplines.enqueue(new Callback<GetAllDisciplines>() {
            @Override
            public void onResponse(Call<GetAllDisciplines> call, Response<GetAllDisciplines> response) {
                //    Log.i("LizatestUpdDiscipline", response.body().getData().get(0).getName());
                //    Log.i("LizatestUpdDiscipline", response.raw().toString());
                disciplinesArray = response.body().getData();
                specialty.setAdapter(disciplinesAdapter[0]);
            }

            @Override
            public void onFailure(Call<GetAllDisciplines> call, Throwable t) {
                //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });
        preDiscipline.setSelection(specialtiesAdapter[0].getPosition(disciplineForUpdate.getPreDiscipline()));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<GetStatus> deleteDiscipline = client.deleteDiscipline(disciplineForUpdate.getId(), token);
                deleteDiscipline.enqueue(new Callback<GetStatus>() {
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

        updateDiscipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disciplineForUpdate.setName(fullName.getText().toString());
                disciplineForUpdate.setSelfWorkTime(Integer.valueOf(selfWorkTime.getText().toString()));
                Specialty newSpecialty = (Specialty) specialty.getSelectedItem();
                disciplineForUpdate.setSpecialtyId(newSpecialty.getId());
                Discipline newDiscipline = (Discipline) preDiscipline.getSelectedItem();
                disciplineForUpdate.setPreDisciplineId(newDiscipline.getId());

                final Call<Discipline> updateDiscipline = client.updateDiscipline(disciplineForUpdate.getId(),getIntent().getStringExtra("token"), disciplineForUpdate);

                updateDiscipline.enqueue(new Callback<Discipline>() {

                    @Override
                    public void onResponse(Call<Discipline> call, Response<Discipline> response) {
                    //    Log.i("LizatestError",response.raw().toString());
                        closeActivity();
                    }

                    @Override
                    public void onFailure(Call<Discipline> call, Throwable t) {
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
