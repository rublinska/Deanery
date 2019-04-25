package com.example.deanery.activities.discipline;

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
import com.example.deanery.activities.discipline.DisciplineRecyclerViewAdapter;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.discipline.Discipline;

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
public class DisciplineFragment extends Fragment implements RefreshInterface {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);

    static String token = "";
    List<Discipline> disciplines = new ArrayList<>();
    static SwipeRefreshLayout swipeRefresh;
    DisciplineRecyclerViewAdapter disciplinesAdapter;
    RecyclerView recyclerView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DisciplineFragment() {
    }

    // TODO: Customize parameter initialization
    public static DisciplineFragment newInstance(SwipeRefreshLayout swipe) {
        DisciplineFragment fragment = new DisciplineFragment();

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
        final View view = inflater.inflate(R.layout.fragment_discipline_list, container, false);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<DeaneryGetList<Discipline>> getData = client.getAllDisciplines(token);
        getData.enqueue(new Callback<DeaneryGetList<Discipline>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Discipline>> call, Response<DeaneryGetList<Discipline>> response) {
            //    Log.i("Lizatest", response.body().getData().get(0).getFullName());
                if(response.body().getData().size() > 0){
                    disciplines = response.body().getData();
                    disciplinesAdapter = new DisciplineRecyclerViewAdapter(disciplines, token, getActivity());
                    recyclerView.setAdapter(disciplinesAdapter);

                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Discipline>> call, Throwable t) {

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
        Call<DeaneryGetList<Discipline>> getAllDisciplines = client.getAllDisciplines(token);
        getAllDisciplines.enqueue(new Callback<DeaneryGetList<Discipline>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Discipline>> call, Response<DeaneryGetList<Discipline>> response) {
                //    Log.i("Lizatest", call.request().url().toString());
                if(response.body().getData().size() > 0){
                    disciplines = (ArrayList<Discipline>) response.body().getData();

                    disciplinesAdapter = new DisciplineRecyclerViewAdapter(disciplines, token, getActivity());
                    recyclerView.setAdapter(disciplinesAdapter);
                    swipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<DeaneryGetList<Discipline>> call, Throwable t) {

            }
        });
    }
}
