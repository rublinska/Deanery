package com.example.deanery.activities.lecturer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
import com.example.deanery.RefreshInterface;
import com.example.deanery.ServiceGenerator;
import com.example.deanery.activities.MainActivity;
import com.example.deanery.activities.department.DepartmentRecyclerViewAdapter;
import com.example.deanery.activities.student.StudentFragment;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.lecturer.Lecturer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class LecturerFragment extends Fragment implements RefreshInterface {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);

    static String token = "";
    List<Lecturer> lecturers = new ArrayList<>();

    LecturerRecyclerViewAdapter lecturerAdapter;
    static SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LecturerFragment() {
    }


    public static LecturerFragment newInstance(SwipeRefreshLayout swipe) {
        LecturerFragment fragment = new LecturerFragment();

        swipeRefresh = swipe;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = new MainActivity();
        token = activity.getToken();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lecturer_list, container, false);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


//      token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1NTI1MDQwODcsImV4cCI6MTU1MjUwNzY4NywibmJmIjoxNTUyNTA0MDg3LCJqdGkiOiJDT2RpaXdJZzRPV3lBT3NCIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.O-yZKKxEC4Mt5QPIqxHLTJVT_Sx1Zwzy0w2AIixE8to"
        Call<DeaneryGetList<Lecturer>> getLecturerData = client.getAllLecturers(token);
        getLecturerData.enqueue(new Callback<DeaneryGetList<Lecturer>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Lecturer>> call, Response<DeaneryGetList<Lecturer>> response) {
            //    Log.i("Lizatest", call.request().url().toString());
            //    Log.i("Lizatest", response.body().getData().get(0).getFullName());
                if(response.body().getData().size() > 0){
                    lecturers = response.body().getData();

                    lecturerAdapter = new LecturerRecyclerViewAdapter(lecturers, token, getActivity());
                    recyclerView.setAdapter(lecturerAdapter);

                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Lecturer>> call, Throwable t) {

            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void refreshItems() {
        Call<DeaneryGetList<Lecturer>> getAllLecturers = client.getAllLecturers(token);
        getAllLecturers.enqueue(new Callback<DeaneryGetList<Lecturer>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Lecturer>> call, Response<DeaneryGetList<Lecturer>> response) {
                //    Log.i("Lizatest", call.request().url().toString());
                //Log.i("LizatestArraySize", String.valueOf(response.body().getData().size()));
                if(response.body().getData().size() > 0){
                    lecturers = (ArrayList<Lecturer>) response.body().getData();


                    lecturerAdapter = new LecturerRecyclerViewAdapter(lecturers, token, getActivity());
                    recyclerView.setAdapter(lecturerAdapter);
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Lecturer>> call, Throwable t) {

            }
        });

    }
}
