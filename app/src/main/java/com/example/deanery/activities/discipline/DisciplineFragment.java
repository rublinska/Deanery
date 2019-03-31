package com.example.deanery.activities.discipline;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deanery.DeaneryAPI;
import com.example.deanery.R;
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
public class DisciplineFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    final DeaneryAPI client = ServiceGenerator.createService(DeaneryAPI.class);

    static String token = "";
    List<Discipline> disciplines = new ArrayList<>();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DisciplineFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DisciplineFragment newInstance(int columnCount) {
        DisciplineFragment fragment = new DisciplineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
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

//      token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwMDAvYXBpL2F1dGgvbG9naW4iLCJpYXQiOjE1NTI1MDQwODcsImV4cCI6MTU1MjUwNzY4NywibmJmIjoxNTUyNTA0MDg3LCJqdGkiOiJDT2RpaXdJZzRPV3lBT3NCIiwic3ViIjoxLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.O-yZKKxEC4Mt5QPIqxHLTJVT_Sx1Zwzy0w2AIixE8to"
        Call<DeaneryGetList<Discipline>> getData = client.getAllDisciplines(token);
        getData.enqueue(new Callback<DeaneryGetList<Discipline>>() {
            @Override
            public void onResponse(Call<DeaneryGetList<Discipline>> call, Response<DeaneryGetList<Discipline>> response) {
            //    Log.i("Lizatest", call.request().url().toString());
            //    Log.i("Lizatest", response.body().getData().get(0).getFullName());
                if(response.body().getData().size() > 0){
                    disciplines = response.body().getData();

                    // Set the adapter
                    if (view instanceof RecyclerView) {
                        Context context = view.getContext();
                        RecyclerView recyclerView = (RecyclerView) view;
                        if (mColumnCount <= 1) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        } else {
                            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                        }
                        recyclerView.setAdapter(new DisciplineRecyclerViewAdapter(disciplines, token, getActivity()));
                    }

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
}
