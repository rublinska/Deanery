package com.example.deanery.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.dataModels.Lecturer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LecturerCreateActivity extends AppCompatActivity {

    final DeaneryAPI client =  ServiceGenerator.createService(DeaneryAPI.class);

    TextView fullName;
    TextView department;
    TextView phone;
    TextView position;
    Button cancel;
    Button createNewLecturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_create);
        //   Toolbar toolbar = findViewById(R.id.toolbar_custom); setSupportActionBar(toolbar);

        fullName = (TextView) findViewById(R.id.full_name);
        department = (TextView) findViewById(R.id.department_id);
        phone = (TextView) findViewById(R.id.phone_number);
        position = (TextView) findViewById(R.id.position);
        cancel = (Button) findViewById(R.id.cancel);
        createNewLecturer = (Button) findViewById(R.id.submit);

        createNewLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Lecturer newLecturer = new Lecturer(fullName.getText().toString(),position.getText().toString(),phone.getText().toString(),Integer.parseInt(department.getText().toString()));

            //    Log.i("Lizatest", getIntent().getStringExtra("token"));
                final Call<Lecturer> createLecturer = client.createLecturer(getIntent().getStringExtra("token"), newLecturer);

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
