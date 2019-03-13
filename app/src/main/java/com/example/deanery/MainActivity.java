package com.example.deanery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.deanery.dataModels.GetLecturer;
import com.example.deanery.dataModels.GetLecturersData;
import com.example.deanery.dataModels.Lecturer;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);
    static String token = "";
    List<Lecturer> lecturers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = getIntent().getExtras().getString("token");

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fillWithData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_students) {
            // Handle the camera action
        } else if (id == R.id.nav_disciplines) {

        } else if (id == R.id.nav_teachers) {

        } else if (id == R.id.nav_create_report) {

        } else if (id == R.id.nav_schedule_cells) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void fillWithData(){
        LinearLayout lecturersLayout = findViewById(R.id.lecturers_layout);


        LinearLayout lecturerLayout = findViewById(R.id.lecturer_layout);
        LinearLayout lecturer1Layout = findViewById(R.id.name_department_layout);
        LinearLayout lecturer2Layout = findViewById(R.id.phone_position_layout);
        final TextView full_name = (TextView) findViewById(R.id.lecturer_full_name);
        final TextView department = (TextView) findViewById(R.id.lecturer_department);
        final TextView phone = (TextView) findViewById(R.id.lecturer_phone);
        final TextView position = (TextView) findViewById(R.id.lecturer_position);

//      token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1NTI1MDQwODcsImV4cCI6MTU1MjUwNzY4NywibmJmIjoxNTUyNTA0MDg3LCJqdGkiOiJDT2RpaXdJZzRPV3lBT3NCIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.O-yZKKxEC4Mt5QPIqxHLTJVT_Sx1Zwzy0w2AIixE8to"
        Call<GetLecturer> getLecturer = client.lecturer(token/*, 1*/);

        getLecturer.enqueue(new Callback<GetLecturer>() {
            @Override
            public void onResponse(Call<GetLecturer> call, Response<GetLecturer> response) {
                if (response.isSuccessful()) {
                    Log.i("Liza", call.request().toString());
                    Log.i("Liza", response.body().getLecturer().getFullName());
                    Lecturer lecturer = response.body().getLecturer();
                    full_name.setText(lecturer.getFullName());
                    department.setText(lecturer.getDepartmentId().toString());
                    phone.setText(lecturer.getPhoneNumber());
                    position.setText(lecturer.getPosition());
//                    Log.i("LizaTest", lecturer.getFullName());
                }
            }

            @Override
            public void onFailure(Call<GetLecturer> call, Throwable t) {
            }
        });



    }

}
