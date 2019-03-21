package com.example.deanery.activities.discipline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.discipline.Discipline;
import com.example.deanery.dataModels.discipline.GetAllDisciplines;
import com.example.deanery.dataModels.specialty.GetAllSpecialties;
import com.example.deanery.dataModels.specialty.Specialty;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisciplineCreateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);
    String token;

    EditText fullName;
    EditText selfWorkTime;

    CheckBox specialtyCheckbox;
    CheckBox preDiscCheckbox;
    Spinner specialtySpinner;
    Spinner disciplineSpinner;
    List<Specialty> specialtiesArray;
    List<Discipline> disciplinesArray = new ArrayList<>();


    Button cancel;
    Button createNewDiscipline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_create);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);
        token = getIntent().getStringExtra("token");
        fullName = findViewById(R.id.discipline_name);
        selfWorkTime = findViewById(R.id.discipline_selfWorkTime);
        specialtySpinner = findViewById(R.id.discipline_specialty);
        disciplineSpinner = findViewById(R.id.discipline_preDiscipline);

        specialtyCheckbox = findViewById(R.id.discipline_spec_checkbox);
        specialtyCheckbox.setChecked(false);
        specialtySpinner.setEnabled(false);
        specialtyCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialtySpinner.setEnabled(specialtyCheckbox.isChecked());
            }
        });

        preDiscCheckbox = findViewById(R.id.discipline_predisc_checkbox);
        preDiscCheckbox.setChecked(false);
        disciplineSpinner.setEnabled(false);
        preDiscCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disciplineSpinner.setEnabled(preDiscCheckbox.isChecked());
            }
        });
        cancel = findViewById(R.id.cancel);
        createNewDiscipline = findViewById(R.id.create);

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
                disciplineSpinner.setAdapter(disciplinesAdapter);
            }
            @Override
            public void onFailure(Call<GetAllDisciplines> call, Throwable t) {
                //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });

        createNewDiscipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Discipline newDiscipline = new Discipline(fullName.getText().toString(),Integer.valueOf(selfWorkTime.getText().toString()));


                if (specialtyCheckbox.isChecked())
                    newDiscipline.setSpecialtyId(((Specialty) specialtySpinner.getSelectedItem()).getId());

                if (preDiscCheckbox.isChecked())
                    newDiscipline.setPreDisciplineId(((Discipline) disciplineSpinner.getSelectedItem()).getId());

                final Call<Discipline> createDiscipline = client.createDiscipline(token, newDiscipline);
                createDiscipline.enqueue(new Callback<Discipline>() {
                    @Override
                    public void onResponse(Call<Discipline> call, Response<Discipline> response) {
                        Log.i("LizatestCreateReponse", response.raw().toString());
                        closeActivity();
                    }
                    @Override
                    public void onFailure(Call<Discipline> call, Throwable t) {
                        Log.i("LizatestCreateFailore", t.getLocalizedMessage());
                        Log.i("LizatestCreateFailore", t.getMessage());
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
