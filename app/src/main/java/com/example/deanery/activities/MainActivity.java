package com.example.deanery.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.RefreshInterface;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.activities.schedule.ScheduleFragment;
import com.example.deanery.activities.schedule.ScheduleCreateActivity;
import com.example.deanery.activities.student.StudentCreateActivity;
import com.example.deanery.activities.student.StudentFragment;
import com.example.deanery.activities.department.DepartmentCreateActivity;
import com.example.deanery.activities.department.DepartmentFragment;
import com.example.deanery.activities.lecturer.LecturerCreateActivity;
import com.example.deanery.activities.lecturer.LecturerFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);
    static String token = "";
    static Integer lastItemId = 0;
    FloatingActionButton fab;
    DrawerLayout drawer;
    SwipeRefreshLayout swipeLayout;
    FragmentManager fragmentManager;
    Context context;

    public String getToken () {
        return token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = getIntent().getExtras().getString("token");
        context = this;
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        swipeLayout = findViewById(R.id.swipeRefreshLayout);
        setFragment();
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
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
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        drawer = findViewById(R.id.drawer_layout);

        lastItemId = menuItem.getItemId();
        setFragment();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();

        return true;
    }

/*

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(departmentName);
        out.writeString(position);
        out.writeString(phoneNumber);
        out.writeInt(departmentId);
        out.writeString(createdAt);
        out.writeString(updatedAt);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator() {
        public ObjectB createFromParcel(Parcel in) {
            return new ObjectB(in);
        }

        public ObjectB[] newArray(int size) {
            return new ObjectB[size];
        }
    };
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == 10001)) {
            setFragment();/*
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);*/
        }
    }

    private void setFragment () {
        final Fragment fragment;
        final Intent intent;
        switch(lastItemId) {

            case R.id.nav_departments:
                /*fragmentClass = DepartmentFragment.class;*/
                intent = new Intent(getApplicationContext(), DepartmentCreateActivity.class);
                fragment = DepartmentFragment.newInstance(swipeLayout);
                break;
            case R.id.nav_teachers:
                fragment = LecturerFragment.newInstance(swipeLayout);
                intent = new Intent(getApplicationContext(), LecturerCreateActivity.class);
                break;
            case R.id.nav_students:
                fragment = StudentFragment.newInstance(swipeLayout);
                intent = new Intent(getApplicationContext(), StudentCreateActivity.class);
                break;
        /*  case R.id.nav_disciplines:
                fragment = DisciplineFragment.newInstance(swipeLayout);
                intent = new Intent(getApplicationContext(), DisciplineCreateActivity.class);
                break;*/
         /*   case R.id.nav_create_report:
                fragmentClass = UniReportFragment.class;
                intent = new Intent(getApplicationContext(), UniReportCreateActivity.class);
                break;*/
            case R.id.nav_schedule_cells:
                fragment = ScheduleFragment.newInstance(swipeLayout);
                intent = new Intent(getApplicationContext(), ScheduleCreateActivity.class);
                break;
            default:
                intent = new Intent(getApplicationContext(), DepartmentCreateActivity.class);
                fragment = DepartmentFragment.newInstance(swipeLayout);
                /*fragmentClass = LecturerFragment.class;
                intent = new Intent(getApplicationContext(), LecturerCreateActivity.class);*/
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("token", token);
                context.startActivity(intent);
            }
        });

        // Insert the fragment by replacing any existing fragment
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ((RefreshInterface) fragment).refreshItems();
            }
        });
    }

}
