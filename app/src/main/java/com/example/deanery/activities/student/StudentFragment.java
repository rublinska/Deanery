package com.example.deanery.activities.student;

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
import com.example.deanery.activities.department.DepartmentFragment;
import com.example.deanery.activities.department.DepartmentRecyclerViewAdapter;
import com.example.deanery.activities.lecturer.LecturerRecyclerViewAdapter;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.student.Student;

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
public class StudentFragment extends Fragment implements RefreshInterface {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);

    static String token = "";
    List<Student> students = new ArrayList<>();

    StudentRecyclerViewAdapter studentAdapter;
    static SwipeRefreshLayout swipeRefresh;
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StudentFragment() {
    }

    public static StudentFragment newInstance(SwipeRefreshLayout swipe) {
        StudentFragment fragment = new StudentFragment();

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
        final View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<DeaneryGetList<Student>> getData = client.getAllStudents(token);
        getData.enqueue(new Callback<DeaneryGetList<Student>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Student>> call, Response<DeaneryGetList<Student>> response) {
            //    Log.i("Lizatest", response.body().getData().get(0).getFullName());
                if(response.body().getData().size() > 0){
                    students = response.body().getData();

                    studentAdapter = new StudentRecyclerViewAdapter(students, token, getActivity());
                    recyclerView.setAdapter(studentAdapter);

                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Student>> call, Throwable t) {

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
        Call<DeaneryGetList<Student>> getAllStudents = client.getAllStudents(token);
        getAllStudents.enqueue(new Callback<DeaneryGetList<Student>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Student>> call, Response<DeaneryGetList<Student>> response) {
                //Log.i("LizatestArraySize", String.valueOf(response.body().getData().size()));
                if (response.body().getData().size() > 0) {
                    students = (ArrayList<Student>) response.body().getData();
                    studentAdapter = new StudentRecyclerViewAdapter(students, token, getActivity());
                    recyclerView.setAdapter(studentAdapter);
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Student>> call, Throwable t) {

            }
        });
    }
}
