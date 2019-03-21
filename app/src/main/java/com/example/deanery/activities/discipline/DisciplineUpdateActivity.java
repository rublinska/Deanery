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

    CheckBox specialtyCheckbox;
    CheckBox preDiscCheckbox;
    Spinner specialtySpinner;
    Spinner disciplineSpinner;
    List<Specialty> specialtiesArray;
    List<Discipline> disciplinesArray = new ArrayList<>();

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

        specialtySpinner = findViewById(R.id.discipline_specialty);
        disciplineSpinner = findViewById(R.id.discipline_preDiscipline);
        specialtyCheckbox = findViewById(R.id.discipline_spec_checkbox);
        specialtyCheckbox.setChecked(disciplineForUpdate.getSpecialtyId()!= null);
        specialtySpinner.setEnabled(specialtyCheckbox.isChecked());
        specialtyCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialtySpinner.setEnabled(specialtyCheckbox.isChecked());
            }
        });

        preDiscCheckbox = findViewById(R.id.discipline_predisc_checkbox);
        preDiscCheckbox.setChecked(disciplineForUpdate.getPreDisciplineId()!= null);
        disciplineSpinner.setEnabled(preDiscCheckbox.isChecked());
        preDiscCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                disciplineSpinner.setEnabled(preDiscCheckbox.isChecked());
            }
        });

        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        updateDiscipline = findViewById(R.id.update);

        fullName.setText(disciplineForUpdate.getName());
        selfWorkTime.setText(disciplineForUpdate.getSelfWorkTime().toString());

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
                if(disciplineForUpdate.getSpecialtyId() != null)
                {
                    for (int i = 0; i < specialtiesAdapter.getCount(); i++) {
                        //    Log.i("LizatestCreateLecturer", String.valueOf(((Department) adapter.getItem(i)).getName().equals(lecturerForUpdate.getDepartment().getName())));
                        //    Log.i("LizatestCreateLecturer", String.valueOf(adapter.getItem(position) == lecturerForUpdate.getDepartment().getName()));
                        if (((Specialty) specialtiesAdapter.getItem(i)).getName().equals(disciplineForUpdate.getSpecialty().getName())) {
                            specialtySpinner.setSelection(i);
                        }
                    }
                }
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
                for (Discipline d : response.body().getData()) {
                    if (!d.getId().equals(disciplineForUpdate.getId()))
                        disciplinesArray.add(d);

                }
                ArrayAdapter disciplinesAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, disciplinesArray);
                disciplinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                disciplineSpinner.setAdapter(disciplinesAdapter);
                if(disciplineForUpdate.getPreDisciplineId() != null)
                {
                    for (int i = 0; i < disciplinesAdapter.getCount(); i++) {
                        //    Log.i("LizatestCreateLecturer", String.valueOf(((Department) adapter.getItem(i)).getName().equals(lecturerForUpdate.getDepartment().getName())));
                        //    Log.i("LizatestCreateLecturer", String.valueOf(adapter.getItem(position) == lecturerForUpdate.getDepartment().getName()));
                        if (((Discipline) disciplinesAdapter.getItem(i)).getName().equals(disciplineForUpdate.getPreDiscipline().getName())) {
                            disciplineSpinner.setSelection(i);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<GetAllDisciplines> call, Throwable t) {
                //    Log.i("LizatestError", String.valueOf(call.isExecuted()));
            }
        });

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

                if (specialtyCheckbox.isChecked())
                    disciplineForUpdate.setSpecialtyId(((Specialty) specialtySpinner.getSelectedItem()).getId());
                else
                    disciplineForUpdate.setSpecialtyId(null);

                if (preDiscCheckbox.isChecked())
                    disciplineForUpdate.setPreDisciplineId(((Discipline) disciplineSpinner.getSelectedItem()).getId());
                else
                    disciplineForUpdate.setPreDisciplineId(null);

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
